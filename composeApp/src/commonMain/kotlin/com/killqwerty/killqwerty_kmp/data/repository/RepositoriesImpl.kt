package com.killqwerty.killqwerty_kmp.data.repository

import com.killqwerty.killqwerty_kmp.data.news.NewsModel
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
        val baseId = (page - 1) * pageSize
        return newsApiService.getTopHeadlines(page = page, pageSize = pageSize).mapIndexed { index, item ->
            NewsModel(
                id = baseId + index + 1,
                text = item.title?.takeIf { it.isNotBlank() } ?: "Untitled",
                description = item.description ?: item.content ?: "No description"
            )
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