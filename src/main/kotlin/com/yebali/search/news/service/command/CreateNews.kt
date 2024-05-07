package com.yebali.search.news.service.command

import com.yebali.search.keyword.entity.Keyword
import java.time.OffsetDateTime

interface CreateNews {
    data class Command(
        val keyword: Keyword,
        val news: List<NewsDTO>,
    ) {
        data class NewsDTO(
            val title: String,
            val link: String,
            val subLink: String?,
            val publishedAt: OffsetDateTime,
            val provider: String,
        )
    }
}
