package com.example.test_task.history.ui.adapter

import com.example.test_task.search.domain.model.BinInfoCard

fun interface HistoryClickListener {
    fun onCardClick(card: BinInfoCard)
}