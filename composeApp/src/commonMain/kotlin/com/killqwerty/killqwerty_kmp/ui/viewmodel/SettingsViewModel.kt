package com.killqwerty.killqwerty_kmp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killqwerty.killqwerty_kmp.domain.interactor.settings.IncrementSettingsCounterInteractor
import com.killqwerty.killqwerty_kmp.domain.interactor.settings.ObserveSettingsCounterInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SettingsState(
    val count: Int = 0
)

sealed interface SettingsEvent {
    data object Increment : SettingsEvent
}

class SettingsViewModel(
    observeSettingsCounterInteractor: ObserveSettingsCounterInteractor,
    private val incrementSettingsCounterInteractor: IncrementSettingsCounterInteractor
) : ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            observeSettingsCounterInteractor().collect { value ->
                _state.update { it.copy(count = value) }
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            SettingsEvent.Increment -> {
                viewModelScope.launch {
                    incrementSettingsCounterInteractor()
                }
            }
        }
    }
}