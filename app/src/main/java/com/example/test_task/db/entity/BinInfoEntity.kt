package com.example.test_task.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class SearchHistoryInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
//    val query: String,
    val brand: String?,
    val scheme: String?,
    val type: String?,
    val prepaid: Boolean?,
    val bankName: String?,
    val bankCity: String?,
    val bankPhone: String?,
    val bankUrl: String?,
    val countryName: String?,
    val countryAlpha2: String?,
    val countryCurrency: String?,
    val countryEmoji: String?,
    val countryLatitude: Int?,
    val countryLongitude: Int?,
    val countryNumeric: String?,
    val numberLength: Int?,
    val numberLuhn: Boolean?
)
