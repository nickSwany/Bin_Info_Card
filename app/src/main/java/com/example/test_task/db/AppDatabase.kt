package com.example.test_task.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test_task.db.dao.SearchHistoryDao
import com.example.test_task.db.entity.SearchHistoryInfoEntity

@Database(entities = [SearchHistoryInfoEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao

}