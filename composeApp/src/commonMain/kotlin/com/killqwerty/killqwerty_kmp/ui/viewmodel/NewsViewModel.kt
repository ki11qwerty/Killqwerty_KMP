package com.killqwerty.killqwerty_kmp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killqwerty.killqwerty_kmp.data.news.NewsModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class NewsState(
    val count: Int = 0,
    val news : List<NewsModel> = emptyList(),
    val isLoading : Boolean = true
)

fun mockList() : List<NewsModel> {
    return List(1000){ x ->  NewsModel(x,"title $x", "All work and no play makes Jack a dull boy\n" +
            "All work and no play makes Jack a dull boy\n" +
            "All work and no play makes Jack a dull boy\n" +
            "All work and no play makes\n" +
            "Jack a dull boy\n" +
            "All work and no play makes\n" +
            "Jack a dull boy\n" +
            "All work and no play makes\n" +
            "Jack a dull boy\n" +
            "All work and no play makes\n" +
            "Jack a dull boy\n" +
            "All work and no play makes Jack a dull boy\n" +
            "All work and no play makes Jack a dull boy\n" +
            "All work and no play makes Jack a dull boy\n" +
            "All work and no play makes Jack a dull boy\n" +
            "All work and no play makes Jack a dull boy" ) }
}

sealed interface NewsEvent {
    data object onStart : NewsEvent
    data object Increment : NewsEvent
}

class NewsViewModel : ViewModel() {
    private val _state = MutableStateFlow(NewsState())
    val state: StateFlow<NewsState> = _state.asStateFlow()


    fun onEvent(event: NewsEvent) {
        when (event) {
            NewsEvent.Increment -> {
                _state.update { it.copy(count = it.count + 1) }
            }
            NewsEvent.onStart -> {
                viewModelScope.launch {
                    delay(1000)
                    val newList = mockList()
                    _state.update { it.copy(news = newList, isLoading = false) }
                }
            }
        }
    }
}