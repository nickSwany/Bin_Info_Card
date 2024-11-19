package com.example.test_task.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task.search.domain.model.Resource
import com.example.test_task.util.debounce
import com.example.test_task.history.HistoryInteractor
import com.example.test_task.history.ui.HistoryViewModel
import com.example.test_task.search.domain.api.SearchInteractor
import com.example.test_task.search.domain.model.BinInfoCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(
    private val searchInteractor: SearchInteractor,
    private val historyViewModel: HistoryViewModel,
    private val historyInteractory: HistoryInteractor
) : ViewModel() {

    private val previousRequest: String = ""

    val searchDebounce = debounce<String>(
        SEARCH_DEBOUNCE_DELAY,
        viewModelScope,
        true
    ) { request ->
        if (request != previousRequest) {
            searchCard(request)
        }
    }

    private val screenState = MutableLiveData<SearchScreenState>()
    fun getScreenState(): LiveData<SearchScreenState> = screenState

    private fun renderState(state: SearchScreenState) {
        screenState.postValue(state)
    }

    init {
        screenState.postValue(SearchScreenState.Default)
    }

    private fun searchCard(bin: String) {
        if (bin.length != 8) {
            renderState(SearchScreenState.Default)
            return
        }
        renderState(SearchScreenState.Loading)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                searchInteractor.getInfo(bin)
                    .collect { resource ->
                        when (resource) {
                            is Resource.Success -> {
                                val cardInfo = resource.data
                                if (cardInfo != null) {
                                    renderState(SearchScreenState.Success(listOf(cardInfo)))
                                    addInHistory(cardInfo)
                                } else {
                                    renderState(SearchScreenState.Default)
                                }
                            }
                            is Resource.Error -> renderState(SearchScreenState.Error)
                        }
                    }
            }
        }
    }

    private suspend fun addInHistory(card: BinInfoCard) {
        historyInteractory.addInHistory(card)
        historyViewModel.fillData()
    }

    fun clear() {
        renderState(SearchScreenState.Default)
    }

    fun emailTo(email: String) {
        searchInteractor.emailTo(email)
    }

    fun callTo(phone: String) {
        searchInteractor.callTo(phone)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }
}
