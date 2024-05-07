package com.yebali.search.catetory.repository

import com.yebali.search.catetory.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long>
