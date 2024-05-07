package com.yebali.search.crawling.service.command

import com.yebali.search.crawling.service.crawler.NewsCrawler.ProviderType
import com.yebali.search.crawling.service.crawler.model.CrawlResultModel
import java.time.OffsetDateTime

interface CrawlNews {
    data class Command(
        val providerType: ProviderType?,
        val query: String,
    )

    data class Result(
        val news: List<NewsDTO>,
    ) {
        data class NewsDTO(
            val title: String,
            val link: String,
            val originalLink: String,
            val publishedDateTime: OffsetDateTime,
            val providerType: String,
        )

        companion object {
            fun of(results: List<CrawlResultModel>) =
                Result(
                    news =
                        results.map {
                            NewsDTO(
                                title = it.title,
                                link = it.link,
                                originalLink = it.originalLink,
                                publishedDateTime = it.publishedDateTime,
                                providerType = it.providerType,
                            )
                        },
                )
        }
    }
}
