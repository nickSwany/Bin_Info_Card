package com.example.test_task.search.domain.impl

import com.example.test_task.search.domain.api.ExternalNavigator
import com.example.test_task.search.domain.model.Resource
import com.example.test_task.search.domain.api.SearchInteractor
import com.example.test_task.search.domain.api.SearchRepository
import com.example.test_task.search.domain.model.BinInfoCard
import kotlinx.coroutines.flow.Flow

class SearchInteractorImpl(private val searchRepository: SearchRepository,
    private val navigate: ExternalNavigator) :  SearchInteractor {

    override fun getInfo(bin: String): Flow<Resource<BinInfoCard>> {
    return searchRepository.getInfo(bin)
    }

    override fun emailTo(email: String) {
        navigate.emailTo(email)
    }

    override fun callTo(phone: String) {
        navigate.callTo(phone)
    }


}