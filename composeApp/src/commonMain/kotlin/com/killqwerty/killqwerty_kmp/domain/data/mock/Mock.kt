package com.killqwerty.killqwerty_kmp.domain.data.mock

import com.killqwerty.killqwerty_kmp.domain.data.NewsModel
import com.killqwerty.killqwerty_kmp.domain.data.Source

class Mock{}


fun Mock.toModel(): NewsModel {
    return NewsModel(
        source = Source("id", "name"),
        author = "author",
        title = "title",
        description = "description",
        url = "url",
        urlToImage = "urlToImage",
        publishedAt = "publishedAt",
        content = "content"
    )
}