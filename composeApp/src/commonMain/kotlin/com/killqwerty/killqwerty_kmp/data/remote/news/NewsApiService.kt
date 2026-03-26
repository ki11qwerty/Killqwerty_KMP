package com.killqwerty.killqwerty_kmp.data.remote.news

import com.killqwerty.killqwerty_kmp.data.news.NewsApiArticleDto
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

interface NewsApiService {
    suspend fun getTopHeadlines(page: Int, pageSize: Int = 100, country: String = "us"): List<NewsApiArticleDto>
    suspend fun getEverything(page: Int, pageSize: Int = 100, language: String = "ru",query : String = "news"): List<NewsApiArticleDto>
}

class NewsApiServiceImpl(
    private val httpClient: HttpClient,
    private val apiKey: String
) : NewsApiService {
    override suspend fun getTopHeadlines(page: Int, pageSize: Int, country: String): List<NewsApiArticleDto> {
        val response = httpClient.get("https://newsapi.org/v2/top-headlines") {
            parameter("apiKey", apiKey)
            parameter("country", country)
            parameter("pageSize", pageSize)
            parameter("page", page)
        }.body<NewsApiResponseDto>()

        return response.articles
    }

    override suspend fun getEverything(
        page: Int,
        pageSize: Int,
        language: String,
        query: String,
    ): List<NewsApiArticleDto> {
        val response = httpClient.get("https://newsapi.org/v2/everything") {
            parameter("apiKey", apiKey)
            parameter("q", query)
            parameter("language", language)
            parameter("pageSize", pageSize)
            parameter("page", page)
        }.body<NewsApiResponseDto>()

        return response.articles
    }
}