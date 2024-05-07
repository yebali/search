package com.yebali.search.crawling.service.crawler

import com.yebali.search.crawling.service.crawler.model.CrawlResultModel

interface NewsCrawler {
    val providerType: ProviderType

    fun crawlNews(query: String): List<CrawlResultModel>

    enum class ProviderType {
        NAVER,
    }
}
