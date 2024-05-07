package com.yebali.search.catetory.entity

import com.yebali.search.common.entity.BaseEntity
import com.yebali.search.keyword.entity.Keyword
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val name: String,
    @OneToMany(mappedBy = "category")
    val keywords: MutableList<Keyword> = mutableListOf(),
) : BaseEntity()
