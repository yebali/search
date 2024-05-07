package com.yebali.search.crawling.service.crawler.model

import java.time.OffsetDateTime

data class CrawlResultModel(
    val title: String,
    val link: String,
    val originalLink: String,
    val publishedDateTime: OffsetDateTime,
    val providerType: String,
)
