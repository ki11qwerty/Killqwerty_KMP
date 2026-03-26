package com.killqwerty.killqwerty_kmp.data.news

import com.killqwerty.killqwerty_kmp.domain.data.NewsModel
import kotlinx.serialization.Serializable

@Serializable
data class NewsApiArticleDto(
    val source : SourceDto? = null,
    val author : String? = null,
    val title: String? = null,
    val description: String? = null,
    val url : String? = null,
    val urlToImage : String? = null,
    val publishedAt : String? = null,
    val content: String? = null
)
@Serializable
data class SourceDto(
    val id : String?,
    val name : String?
)