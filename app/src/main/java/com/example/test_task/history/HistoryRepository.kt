package com.example.test_task.history

import com.example.test_task.search.domain.model.BinInfoCard
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun historyCard() : Flow<List<BinInfoCard>>

    suspend fun addHistory(card: BinInfoCard)
}