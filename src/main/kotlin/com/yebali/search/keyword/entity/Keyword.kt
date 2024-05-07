package com.yebali.search.keyword.entity

import com.yebali.search.catetory.entity.Category
import com.yebali.search.common.entity.BaseEntity
import com.yebali.search.news.entity.News
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class Keyword(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val query: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: Category? = null,
    @OneToMany(mappedBy = "keyword")
    val news: MutableList<News> = mutableListOf(),
) : BaseEntity()
