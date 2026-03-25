package com.killqwerty.killqwerty_kmp.domain.repository

import com.killqwerty.killqwerty_kmp.data.news.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getTopHeadlines(page: Int, pageSize: Int): List<NewsModel>
}

interface SettingsRepository {
    fun observeCounter(): Flow<Int>
    suspend fun incrementCounter()
}

interface TrainingRepository {
    fun observeCounter(): Flow<Int>
    suspend fun incrementCounter()
}