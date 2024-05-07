package com.yebali.search.feignclient

import com.yebali.search.feignclient.rest.FetchNaverNews
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "naver-open-api",
    url = "\${external-api.naver.url}",
)
interface NaverAPIFeign {
    @GetMapping("/news.json")
    fun fetchNews(
        @RequestParam("query") query: String,
        @RequestParam("display") display: Int = 50,
        @RequestParam("sort") sort: String = "sim",
    ): FetchNaverNews.Response
}
