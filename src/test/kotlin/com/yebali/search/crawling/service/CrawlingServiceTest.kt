package com.yebali.search.crawling.service

import com.yebali.search.crawling.service.command.CrawlNews
import com.yebali.search.util.SpringBootTestSupport
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class CrawlingServiceTest(
    @Autowired val crawlingService: CrawlingService,
) : SpringBootTestSupport() {
    @Test
    fun `crawl news`() {
        val result =
            crawlingService.crawlNews(
                CrawlNews.Command(
                    providerType = null,
                    query = "엔비디아",
                ),
            )

        Assertions.assertThat(result.news).isNotEmpty
        result.news.forEach {
            println(it)
        }
    }
}
