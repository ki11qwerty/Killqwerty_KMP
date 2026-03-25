package com.killqwerty.killqwerty_kmp.domain.interactor.news

import com.killqwerty.killqwerty_kmp.data.news.NewsModel
import com.killqwerty.killqwerty_kmp.domain.repository.NewsRepository

class NewsInteractor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(page: Int, pageSize: Int): List<NewsModel> {
        return newsRepository.getTopHeadlines(page = page, pageSize = pageSize)
    }
}