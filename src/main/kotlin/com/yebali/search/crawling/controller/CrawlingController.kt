package com.yebali.search.crawling.controller

import com.yebali.search.crawling.controller.rest.CrawlNewsRest
import com.yebali.search.crawling.service.CrawlingService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CrawlingController(
    val crawlingService: CrawlingService,
) {
    @PostMapping("/crawl")
    fun crawl(
        @RequestBody request: CrawlNewsRest.Request,
    ): CrawlNewsRest.Response {
        return CrawlNewsRest.Response.of(
            keyword = request.query,
            result = crawlingService.crawlNews(request.toCommand()),
        )
    }
}
