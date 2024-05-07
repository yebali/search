package com.yebali.search.crawling.service

import com.yebali.search.crawling.service.command.CrawlNews
import com.yebali.search.crawling.service.crawler.NewsCrawler
import com.yebali.search.crawling.service.crawler.model.CrawlResultModel
import com.yebali.search.keyword.entity.Keyword
import com.yebali.search.keyword.service.KeywordService
import com.yebali.search.news.service.NewsService
import com.yebali.search.news.service.command.CreateNews
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CrawlingService(
    private val crawlers: List<NewsCrawler>,
    private val newsService: NewsService,
    private val keywordService: KeywordService,
) {
    fun crawlNews(command: CrawlNews.Command): CrawlNews.Result {
        val fetchResult = fetchNews(command)
        val keyword = getKeyword(command.query)

        saveNews(keyword, fetchResult)

        return CrawlNews.Result.of(fetchNews(command))
    }

    private fun fetchNews(command: CrawlNews.Command): List<CrawlResultModel> {
        return (
            command.providerType
                ?.let { crawlers.filter { it.providerType == command.providerType } }
                ?: crawlers
        )
            .map { it.crawlNews(command.query) }.flatten()
    }

    private fun getKeyword(query: String): Keyword {
        return keywordService.getOrCreate(query)
    }

    private fun saveNews(
        keyword: Keyword,
        fetchResult: List<CrawlResultModel>,
    ) {
        newsService.createNews(
            command =
                CreateNews.Command(
                    keyword = keyword,
                    news =
                        fetchResult.map {
                            CreateNews.Command.NewsDTO(
                                title = it.title,
                                link = it.link,
                                subLink = it.originalLink,
                                publishedAt = it.publishedDateTime,
                                provider = it.providerType,
                            )
                        },
                ),
        )
    }
}
