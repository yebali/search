package com.yebali.search.keyword.repository

import com.yebali.search.keyword.entity.Keyword
import org.springframework.data.jpa.repository.JpaRepository

interface KeywordRepository : JpaRepository<Keyword, Long> {
    fun findByQuery(query: String): Keyword?
}
