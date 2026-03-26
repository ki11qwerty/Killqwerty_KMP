package com.killqwerty.killqwerty_kmp.domain.interactor.news

import com.killqwerty.killqwerty_kmp.domain.data.NewsModel
import com.killqwerty.killqwerty_kmp.domain.repository.NewsRepository

class NewsInteractor(
    private val newsRepository: NewsRepository
) {

    suspend fun getTopHeadlines(page: Int, pageSize: Int): List<NewsModel> {
        return newsRepository.getTopHeadlines(page = page, pageSize = pageSize)
    }

    suspend fun getEverything(
        page: Int,
        pageSize: Int,
        language: String? = null,
        query: String? = null
    ): List<NewsModel> {
        return newsRepository.getEverything(
            page = page,
            pageSize = pageSize,
            language = language,
            query = query
        )
    }
}