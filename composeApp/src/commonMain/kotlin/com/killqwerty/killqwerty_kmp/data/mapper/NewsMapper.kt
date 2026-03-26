package com.killqwerty.killqwerty_kmp.data.mapper

import com.killqwerty.killqwerty_kmp.data.news.NewsApiArticleDto
import com.killqwerty.killqwerty_kmp.data.news.SourceDto
import com.killqwerty.killqwerty_kmp.domain.data.NewsModel
import com.killqwerty.killqwerty_kmp.domain.data.Source

fun NewsApiArticleDto.toDomain() : NewsModel{
        return NewsModel(
            source?.toDomain(),
            author ?: "",
            title ?: "empty",
            description ?: "empty desc",
            url,
            urlToImage,
            publishedAt ?: "",
            content ?: "empty"
        )
}

fun SourceDto.toDomain() : Source{
    return Source(
        id ,
        name
    )
}