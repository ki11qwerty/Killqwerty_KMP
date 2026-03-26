package com.killqwerty.killqwerty_kmp.domain.repository

import com.killqwerty.killqwerty_kmp.domain.data.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getTopHeadlines(page: Int, pageSize: Int): List<NewsModel>
    suspend fun getEverything(page: Int, pageSize: Int = 100, language: String? = null, query : String? = null): List<NewsModel>
}

interface SettingsRepository {
    fun observeCounter(): Flow<Int>
    suspend fun incrementCounter()
}

interface TrainingRepository {
    fun observeCounter(): Flow<Int>
    suspend fun incrementCounter()
}