package com.yebali.search.keyword.service

import com.yebali.search.keyword.entity.Keyword
import com.yebali.search.keyword.repository.KeywordRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class KeywordService(
    private val keywordRepository: KeywordRepository,
) {
    fun getOrCreate(query: String): Keyword {
        return keywordRepository.findByQuery(query)
            ?: keywordRepository.save(Keyword(query = query))
    }
}
