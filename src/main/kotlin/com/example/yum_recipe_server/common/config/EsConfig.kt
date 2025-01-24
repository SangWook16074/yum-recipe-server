package com.example.yum_recipe_server.common.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchConfiguration
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*


@Configuration
class EsConfig : ReactiveElasticsearchConfiguration() {
    override fun clientConfiguration(): ClientConfiguration {
        return ClientConfiguration.builder()
            .connectedTo("ec2-43-203-35-91.ap-northeast-2.compute.amazonaws.com:9200")
            .usingSsl(disableSSLVerification()!!, allHostsValid())
            .withBasicAuth("elastic", "+l1W2CsmivsKhiSz+NJ*")
            .build()
    }

    private fun disableSSLVerification() : SSLContext? {
        try {
            // ============================================
            // trust manager 생성(인증서 체크 전부 안함)
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<X509Certificate>? {
                    return null
                }

                override fun checkClientTrusted(certs: Array<X509Certificate?>?, authType: String?) {
                }

                override fun checkServerTrusted(certs: Array<X509Certificate?>?, authType: String?) {
                }
            })

            // trust manager 설치
            val sc = SSLContext.getInstance("SSL")
            sc.init(null, trustAllCerts, SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)

            return sc
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }
        return null
    }

    private fun allHostsValid(): HostnameVerifier {
        // ============================================
        // host name verifier 생성(호스트 네임 체크안함)
        val allHostsValid = HostnameVerifier { hostname: String?, session: SSLSession? -> true }
        // host name verifier 설치
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid)
        return allHostsValid
    }
}