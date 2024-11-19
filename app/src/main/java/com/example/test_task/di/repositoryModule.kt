package com.example.test_task.di

import com.example.test_task.history.BinInfoCardConverter
import com.example.test_task.history.HistoryRepository
import com.example.test_task.history.HistoryRepositoryImpl
import com.example.test_task.search.domain.api.SearchRepository
import com.example.test_task.search.domain.impl.SearchRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<SearchRepository> { SearchRepositoryImpl(get(), get())  }

    single<HistoryRepository> { HistoryRepositoryImpl(get(), get()) }

    factory {
        BinInfoCardConverter()
    }
}