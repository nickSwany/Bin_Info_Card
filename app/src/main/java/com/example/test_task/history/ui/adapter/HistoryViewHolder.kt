package com.example.test_task.history.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task.databinding.CardItemBinding
import com.example.test_task.db.entity.SearchHistoryInfoEntity
import com.example.test_task.search.domain.model.BinInfoCard

class HistoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val binding = CardItemBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun bind(info: BinInfoCard) = with(binding) {
        nameTv.text = info.bank.name
        emailTv.text = info.bank.url
        numberTv.text = info.bank.phone
        brandTv.text = info.brand
        tvSchemeNetwork.text = info.scheme
        lengthTv.text = info.number.length.toString()
        luhnTv.text = if (info.number.luhn == true) "Yes" else "No"
        typeTv.text = info.type
        preparidTv.text = if (info.prepaid) "Yes" else "No"
        countryTv.text = info.country.name

    }
}