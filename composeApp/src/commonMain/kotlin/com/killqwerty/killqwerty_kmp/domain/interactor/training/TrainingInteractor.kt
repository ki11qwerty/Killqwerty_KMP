package com.killqwerty.killqwerty_kmp.domain.interactor.training

import com.killqwerty.killqwerty_kmp.domain.repository.TrainingRepository
import kotlinx.coroutines.flow.Flow

class ObserveTrainingCounterInteractor(
    private val trainingRepository: TrainingRepository
) {
    operator fun invoke(): Flow<Int> = trainingRepository.observeCounter()
}

class IncrementTrainingCounterInteractor(
    private val trainingRepository: TrainingRepository
) {
    suspend operator fun invoke() {
        trainingRepository.incrementCounter()
    }
}