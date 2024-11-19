package com.example.test_task.di

import com.example.test_task.history.HistoryInteractor
import com.example.test_task.history.HistoryInteractorImpl
import com.example.test_task.search.domain.api.SearchInteractor
import com.example.test_task.search.domain.impl.SearchInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    single<SearchInteractor> { SearchInteractorImpl(get(), get()) }
    single<HistoryInteractor> { HistoryInteractorImpl(get()) }
}