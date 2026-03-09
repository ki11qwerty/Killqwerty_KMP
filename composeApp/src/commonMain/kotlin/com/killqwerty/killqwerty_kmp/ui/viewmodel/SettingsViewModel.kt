package com.killqwerty.killqwerty_kmp.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SettingsState(
    val count: Int = 0
)

sealed interface SettingsEvent {
    data object Increment : SettingsEvent
}

class SettingsViewModel : ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()

    fun onEvent(event: SettingsEvent) {
        when (event) {
            SettingsEvent.Increment -> {
                _state.update { it.copy(count = it.count + 1) }
            }
        }
    }
}