package com.example.test_task.history

import com.example.test_task.search.domain.model.BinInfoCard
import kotlinx.coroutines.flow.Flow

class HistoryInteractorImpl(private val historyRepository: HistoryRepository) : HistoryInteractor {
    override suspend fun addInHistory(card: BinInfoCard) {
        historyRepository.addHistory(card)
    }

    override suspend fun getHistory(): Flow<List<BinInfoCard>> {
       return historyRepository.historyCard()
    }

}