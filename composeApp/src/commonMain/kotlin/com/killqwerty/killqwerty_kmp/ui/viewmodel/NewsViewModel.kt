package com.killqwerty.killqwerty_kmp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killqwerty.killqwerty_kmp.data.news.NewsModel
import com.killqwerty.killqwerty_kmp.domain.interactor.news.NewsInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class NewsState(
    val news: List<NewsModel> = emptyList(),
    val isLoading: Boolean = false,
    val page: Int = 1,
    val hasMore: Boolean = true,
    val error: String? = null
)

sealed interface NewsEvent {
    data object OnStart : NewsEvent
    data object LoadNextPage : NewsEvent
}

class NewsViewModel(
    private val newsInteractor: NewsInteractor
) : ViewModel() {
    private val _state = MutableStateFlow(NewsState())
    val state: StateFlow<NewsState> = _state.asStateFlow()

    fun onEvent(event: NewsEvent) {
        when (event) {
            NewsEvent.OnStart -> loadNewsPage(reset = true)
            NewsEvent.LoadNextPage -> {
                if (_state.value.isLoading || !_state.value.hasMore) return
                loadNewsPage(reset = false)
            }
        }
    }

    private fun loadNewsPage(reset: Boolean) {
        viewModelScope.launch {
            val nextPage = if (reset) 1 else _state.value.page
            _state.update { it.copy(isLoading = true, error = null) }

            runCatching { newsInteractor(page = nextPage, pageSize = PAGE_SIZE) }
                .onSuccess { pageItems ->
                    _state.update { current ->
                        val merged = if (reset) pageItems else current.news + pageItems
                        current.copy(
                            news = merged,
                            isLoading = false,
                            page = if (pageItems.isEmpty()) current.page else nextPage + 1,
                            hasMore = pageItems.size == PAGE_SIZE,
                            error = null
                        )
                    }
                }
                .onFailure { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = throwable.message ?: "Failed to load news"
                        )
                    }
                }
        }
    }

    private companion object {
        const val PAGE_SIZE = 20
    }
}