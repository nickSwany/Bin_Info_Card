package com.example.test_task.history

import com.example.test_task.search.domain.model.BinInfoCard

sealed interface HistoryState {

    data object Loading: HistoryState

    data object Empty : HistoryState

    data class Content(
        val card : List<BinInfoCard>
    ) : HistoryState
}