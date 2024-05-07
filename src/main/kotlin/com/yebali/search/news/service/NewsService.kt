package com.yebali.search.news.service

import com.yebali.search.news.entity.News
import com.yebali.search.news.repository.NewsRepository
import com.yebali.search.news.service.command.CreateNews
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class NewsService(
    private val newsRepository: NewsRepository,
) {
    fun createNews(command: CreateNews.Command): List<News> {
        val existLinks =
            newsRepository.findAllByLinkInAndKeyword(
                links = command.news.map { it.link },
                keyword = command.keyword,
            ).map { it.link }

        val news =
            command.news
                .filter { it.link !in existLinks }
                .map {
                    News(
                        title = it.title,
                        link = it.link,
                        subLink = it.subLink,
                        publishedAt = it.publishedAt,
                        provider = it.provider,
                        keyword = command.keyword,
                    )
                }

        return newsRepository.saveAll(news)
    }
}
