package com.example.test_task.search.ui

import com.example.test_task.search.domain.model.BinInfoCard

sealed interface SearchScreenState {

     data object Loading : SearchScreenState
     data object Default : SearchScreenState

    data object Error : SearchScreenState
    data class Success(val binInfo: List<BinInfoCard>) : SearchScreenState
}