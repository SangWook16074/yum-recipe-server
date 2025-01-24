package com.example.yum_recipe_server.common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
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

    @Value("\${elasticsearch.host}")
    lateinit var host : String

    @Value("\${elasticsearch.port}")
    lateinit var port : String

    @Value("\${elasticsearch.username}")
    lateinit var username : String

    @Value("\${elasticsearch.password}")
    lateinit var password : String

    @Bean
    override fun clientConfiguration(): ClientConfiguration {
        return ClientConfiguration.builder()
            .connectedTo("$host:$port")
            .usingSsl(disableSSLVerification()!!, allHostsValid())
            .withBasicAuth(username, password)
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