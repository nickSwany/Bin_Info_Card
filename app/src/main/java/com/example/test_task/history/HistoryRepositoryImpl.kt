package com.example.test_task.history


import com.example.test_task.db.AppDatabase
import com.example.test_task.db.entity.SearchHistoryInfoEntity
import com.example.test_task.search.domain.model.BinInfoCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HistoryRepositoryImpl(
   private val appDatabase: AppDatabase,
    private val infoCardConvertor: BinInfoCardConverter
) : HistoryRepository {

    override fun historyCard(): Flow<List<BinInfoCard>> {
        return appDatabase.searchHistoryDao()
            .getAllHistory()
            .map { cards -> convertFromCardEntity(cards) }
    }

    override suspend fun addHistory(card: BinInfoCard) {
       appDatabase.searchHistoryDao().insertHistoryItem(cardConvertorEntity(card))
    }
    
    private fun convertFromCardEntity(cards: List<SearchHistoryInfoEntity>) : List<BinInfoCard> {
        return cards.map { card -> infoCardConvertor.map(card) }
    }

    private fun cardConvertorEntity(card: BinInfoCard) : SearchHistoryInfoEntity {
        return infoCardConvertor.map(card)
    }

}