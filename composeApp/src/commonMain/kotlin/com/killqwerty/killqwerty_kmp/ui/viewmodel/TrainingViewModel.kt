package com.killqwerty.killqwerty_kmp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killqwerty.killqwerty_kmp.domain.interactor.training.IncrementTrainingCounterInteractor
import com.killqwerty.killqwerty_kmp.domain.interactor.training.ObserveTrainingCounterInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TrainingState(
    val count: Int = 0
)

sealed interface TrainingEvent {
    data object Increment : TrainingEvent
}

class TrainingViewModel(
    observeTrainingCounterInteractor: ObserveTrainingCounterInteractor,
    private val incrementTrainingCounterInteractor: IncrementTrainingCounterInteractor
) : ViewModel() {
    private val _state = MutableStateFlow(TrainingState())
    val state: StateFlow<TrainingState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            observeTrainingCounterInteractor().collect { value ->
                _state.update { it.copy(count = value) }
            }
        }
    }

    fun onEvent(event: TrainingEvent) {
        when (event) {
            TrainingEvent.Increment -> {
                viewModelScope.launch {
                    incrementTrainingCounterInteractor()
                }
            }
        }
    }
}