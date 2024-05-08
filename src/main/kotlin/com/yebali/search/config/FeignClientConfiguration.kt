package com.yebali.search.config

import feign.RequestInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableFeignClients
@Configuration
class FeignClientConfiguration(
    @Value("\${external-api.naver.client-id}")
    private val naverClientId: String,
    @Value("\${external-api.naver.client-secret}")
    private val naverClientSecret: String,
) {
    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { template ->
            template.header("X-Naver-Client-Id", naverClientId)
            template.header("X-Naver-Client-Secret", naverClientSecret)
        }
    }
}
