package com.yebali.search.news.repository

import com.yebali.search.keyword.entity.Keyword
import com.yebali.search.news.entity.News
import org.springframework.data.jpa.repository.JpaRepository

interface NewsRepository : JpaRepository<News, Long> {
    fun findAllByLinkInAndKeyword(
        links: List<String>,
        keyword: Keyword,
    ): List<News>
}
