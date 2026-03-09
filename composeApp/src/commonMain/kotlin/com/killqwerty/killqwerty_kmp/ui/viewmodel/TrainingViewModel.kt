package com.killqwerty.killqwerty_kmp.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TrainingState(
    val count: Int = 0
)

sealed interface TrainingEvent {
    data object Increment : TrainingEvent
}

class TrainingViewModel : ViewModel() {
    private val _state = MutableStateFlow(TrainingState())
    val state: StateFlow<TrainingState> = _state.asStateFlow()

    fun onEvent(event: TrainingEvent) {
        when (event) {
            TrainingEvent.Increment -> {
                _state.update { it.copy(count = it.count + 1) }
            }
        }
    }
}