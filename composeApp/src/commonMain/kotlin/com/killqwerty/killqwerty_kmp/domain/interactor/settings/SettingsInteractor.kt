package com.killqwerty.killqwerty_kmp.domain.interactor.settings

import com.killqwerty.killqwerty_kmp.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class ObserveSettingsCounterInteractor(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke(): Flow<Int> = settingsRepository.observeCounter()
}

class IncrementSettingsCounterInteractor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke() {
        settingsRepository.incrementCounter()
    }
}