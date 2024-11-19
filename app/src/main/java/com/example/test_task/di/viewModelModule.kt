package com.example.test_task.di

import com.example.test_task.history.ui.HistoryViewModel
import com.example.test_task.search.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        println("Создаём SearchViewModel...")
        SearchViewModel(get(), get(), get())
    }

    viewModel {
        HistoryViewModel(get())
    }

}