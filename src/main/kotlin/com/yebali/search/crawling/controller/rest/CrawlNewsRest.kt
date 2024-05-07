package com.yebali.search.crawling.controller.rest

import com.yebali.search.crawling.service.command.CrawlNews
import com.yebali.search.crawling.service.command.CrawlNews.Command
import com.yebali.search.crawling.service.crawler.NewsCrawler.ProviderType
import com.yebali.search.crawling.service.crawler.NewsCrawler.ProviderType.NAVER

interface CrawlNewsRest {
    data class Request(
        val providerType: ProviderType = NAVER,
        val query: String,
    ) {
        fun toCommand() =
            Command(
                providerType = providerType,
                query = query,
            )
    }

    data class Response(
        val keyword: String,
        val news: List<News>,
    ) {
        data class News(
            val title: String,
            val link: String,
            val publishedDateTime: String,
            val providerType: String,
        )

        companion object {
            fun of(
                keyword: String,
                result: CrawlNews.Result,
            ) = Response(
                keyword = keyword,
                news =
                    result.news.map {
                        News(
                            title = it.title,
                            link = it.link,
                            publishedDateTime = it.publishedDateTime.toString(),
                            providerType = it.providerType,
                        )
                    },
            )
        }
    }
}
