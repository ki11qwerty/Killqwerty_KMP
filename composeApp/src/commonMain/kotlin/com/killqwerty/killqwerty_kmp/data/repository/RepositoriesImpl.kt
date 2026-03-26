package com.killqwerty.killqwerty_kmp.data.repository

import com.killqwerty.killqwerty_kmp.data.mapper.toDomain
import com.killqwerty.killqwerty_kmp.domain.data.NewsModel
import com.killqwerty.killqwerty_kmp.data.remote.news.NewsApiService
import com.killqwerty.killqwerty_kmp.domain.repository.NewsRepository
import com.killqwerty.killqwerty_kmp.domain.repository.SettingsRepository
import com.killqwerty.killqwerty_kmp.domain.repository.TrainingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewsRepositoryImpl(
    private val newsApiService: NewsApiService
) : NewsRepository {
    override suspend fun getTopHeadlines(page: Int, pageSize: Int): List<NewsModel> {
        return newsApiService.getTopHeadlines(page = page, pageSize = pageSize).map { dto ->
           dto.toDomain()
        }
    }

    override suspend fun getEverything(
        page: Int,
        pageSize: Int,
        language: String?,
        query: String?
    ): List<NewsModel> {
        return newsApiService.getEverything(page = page, pageSize = pageSize).map { dto ->
            dto.toDomain()
        }
    }
}

class SettingsRepositoryImpl : SettingsRepository {
    private val counter = MutableStateFlow(0)

    override fun observeCounter(): Flow<Int> = counter.asStateFlow()

    override suspend fun incrementCounter() {
        counter.update { it + 1 }
    }
}

class TrainingRepositoryImpl : TrainingRepository {
    private val counter = MutableStateFlow(0)

    override fun observeCounter(): Flow<Int> = counter.asStateFlow()

    override suspend fun incrementCounter() {
        counter.update { it + 1 }
    }
}