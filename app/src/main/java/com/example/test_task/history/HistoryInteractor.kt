package com.example.test_task.history

import com.example.test_task.search.domain.model.BinInfoCard
import kotlinx.coroutines.flow.Flow

interface HistoryInteractor {
    suspend fun addInHistory(card: BinInfoCard)
    suspend fun getHistory(): Flow<List<BinInfoCard>>
}