package com.killqwerty.killqwerty_kmp.data.remote.news

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.Serializable

@Serializable
data class NewsApiResponseDto(
    val status: String,
    val totalResults: Int = 0,
    val articles: List<NewsApiArticleDto> = emptyList()
)

@Serializable
data class NewsApiArticleDto(
    val title: String? = null,
    val description: String? = null,
    val content: String? = null
)

interface NewsApiService {
    suspend fun getTopHeadlines(page: Int, pageSize: Int, country: String = "us"): List<NewsApiArticleDto>
}

class NewsApiServiceImpl(
    private val httpClient: HttpClient,
    private val apiKey: String
) : NewsApiService {
    override suspend fun getTopHeadlines(page: Int, pageSize: Int, country: String): List<NewsApiArticleDto> {
        val response = httpClient.get("https://newsapi.org/v2/top-headlines") {
            parameter("apiKey", apiKey)
            parameter("country", country)
            parameter("page", page)
            parameter("pageSize", pageSize)
        }.body<NewsApiResponseDto>()

        return response.articles
    }
}