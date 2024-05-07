package com.yebali.search.news.entity

import com.yebali.search.common.entity.BaseEntity
import com.yebali.search.keyword.entity.Keyword
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.OffsetDateTime

@Entity
@Table(
    indexes = [
        Index(name = "idx_news_link", columnList = "link"),
        Index(name = "idx_news_keyword_id", columnList = "keyword_id"),
    ],
)
class News(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val title: String,
    val link: String,
    val subLink: String?,
    val publishedAt: OffsetDateTime,
    val provider: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    val keyword: Keyword,
) : BaseEntity()
