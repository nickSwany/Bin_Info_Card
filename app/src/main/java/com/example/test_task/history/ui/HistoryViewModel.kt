package com.example.test_task.history.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task.history.HistoryInteractor
import com.example.test_task.history.HistoryState
import com.example.test_task.search.domain.model.BinInfoCard
import kotlinx.coroutines.launch

class HistoryViewModel(private val historyInteractor: HistoryInteractor) : ViewModel() {

    private val _stateLiveData = MutableLiveData<HistoryState>()
    fun stateLiveData(): LiveData<HistoryState> = _stateLiveData

    private val _historyList = MutableLiveData<List<BinInfoCard>>()
    val historyList: LiveData<List<BinInfoCard>> = _historyList

    init{
        fillData()
    }

    fun fillData() {
        renderState(HistoryState.Loading)
        viewModelScope.launch {
            historyInteractor.getHistory().collect { card ->
                Log.d("HistoryViewModel", "Loaded cards: $card")
                processResult(card)
            }
        }
    }

    private fun processResult(cards: List<BinInfoCard>) {
        if (cards.isEmpty()) {
            renderState(HistoryState.Empty)
        } else {
            _historyList.postValue(cards)
            renderState(HistoryState.Content(cards))
        }
    }


    private fun renderState(state: HistoryState) {
        _stateLiveData.postValue(state)
    }

}