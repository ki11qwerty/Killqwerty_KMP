package com.killqwerty.killqwerty_kmp.di

import com.killqwerty.killqwerty_kmp.data.remote.news.NewsApiService
import com.killqwerty.killqwerty_kmp.data.remote.news.NewsApiServiceImpl
import com.killqwerty.killqwerty_kmp.data.repository.NewsRepositoryImpl
import com.killqwerty.killqwerty_kmp.data.repository.SettingsRepositoryImpl
import com.killqwerty.killqwerty_kmp.data.repository.TrainingRepositoryImpl
import com.killqwerty.killqwerty_kmp.domain.interactor.news.NewsInteractor
import com.killqwerty.killqwerty_kmp.domain.interactor.settings.IncrementSettingsCounterInteractor
import com.killqwerty.killqwerty_kmp.domain.interactor.settings.ObserveSettingsCounterInteractor
import com.killqwerty.killqwerty_kmp.domain.interactor.training.IncrementTrainingCounterInteractor
import com.killqwerty.killqwerty_kmp.domain.interactor.training.ObserveTrainingCounterInteractor
import com.killqwerty.killqwerty_kmp.domain.repository.NewsRepository
import com.killqwerty.killqwerty_kmp.domain.repository.SettingsRepository
import com.killqwerty.killqwerty_kmp.domain.repository.TrainingRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

private const val NEWS_API_KEY = "efecdc64f5ff451e89046c36fa09f152"

val appModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            explicitNulls = false
        }
    }

    single {
        HttpClient {
            install(ContentNegotiation) { json(get()) }
            install(Logging) { level = LogLevel.NONE }
        }
    }

    single<NewsApiService> { NewsApiServiceImpl(httpClient = get(), apiKey = NEWS_API_KEY) }

    single<NewsRepository> { NewsRepositoryImpl(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl() }
    single<TrainingRepository> { TrainingRepositoryImpl() }

    factory { NewsInteractor(get()) }
    factory { ObserveSettingsCounterInteractor(get()) }
    factory { IncrementSettingsCounterInteractor(get()) }
    factory { ObserveTrainingCounterInteractor(get()) }
    factory { IncrementTrainingCounterInteractor(get()) }
}

fun initKoinIfNeeded() {
    if (GlobalContext.getOrNull() == null) {
        startKoin {
            modules(appModule)
        }
    }
}