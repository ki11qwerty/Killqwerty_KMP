package com.killqwerty.killqwerty_kmp.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class NewsState(
    val count: Int = 0
)

sealed interface NewsEvent {
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
        }
    }
}