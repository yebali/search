package com.yebali.search.crawling.service.crawler

import com.yebali.search.common.util.LoggingCompanion
import com.yebali.search.crawling.service.crawler.NewsCrawler.ProviderType.NAVER
import com.yebali.search.crawling.service.crawler.model.CrawlResultModel
import com.yebali.search.feignclient.NaverAPIFeign
import org.springframework.stereotype.Component
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Component
class NaverNewCrawler(
    private val naverAPIFeign: NaverAPIFeign,
) : NewsCrawler {
    override val providerType = NAVER

    override fun crawlNews(query: String): List<CrawlResultModel> {
        val response =
            runCatching {
                logger.info("Fetch news from naver api. query=$query")
                naverAPIFeign.fetchNews(query = query)
            }.onFailure { e ->
                logger.error("Failed to fetch news from naver api. query=$query. e = $e")
            }.getOrNull() ?: return emptyList()

        return response.items.map {
            CrawlResultModel(
                title = it.title.trimHtmlTags(),
                link = it.link,
                originalLink = it.originalLink,
                publishedDateTime = it.pubDate.toOffsetDateTime(),
                providerType = this.providerType.name,
            )
        }
    }

    private fun String.toOffsetDateTime(): OffsetDateTime {
        return OffsetDateTime.parse(this, DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US))
    }

    fun String.trimHtmlTags(): String =
        this
            .replace(Regex("<[^>]*>"), "") // HTML 태그 제거
            .replace("&nbsp;", " ")
            .replace("&lt;", "<")
            .replace("&gt;", ">")
            .replace("&quot;", "\"")
            .replace("&#035;", "#")
            .replace("&#039;", "'")

    companion object : LoggingCompanion()
}
