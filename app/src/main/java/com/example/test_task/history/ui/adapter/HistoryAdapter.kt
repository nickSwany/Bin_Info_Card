package com.example.test_task.history.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task.R
import com.example.test_task.search.domain.model.BinInfoCard

class HistoryAdapter(private val searchClickListener: HistoryClickListener) :
    RecyclerView.Adapter<HistoryViewHolder>() {

    var historyList = ArrayList<BinInfoCard>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int = historyList.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyList[position])
        holder.itemView.setOnClickListener {
            searchClickListener.onCardClick(historyList[position])
        }
    }
}