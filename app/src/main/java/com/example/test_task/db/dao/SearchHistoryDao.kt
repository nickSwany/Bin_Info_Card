package com.example.test_task.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test_task.db.entity.SearchHistoryInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryItem(item: SearchHistoryInfoEntity)

    @Query("SELECT * FROM search_history ORDER BY ID DESC")
    fun getAllHistory():Flow<List<SearchHistoryInfoEntity>>

}