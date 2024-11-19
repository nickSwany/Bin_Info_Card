package com.example.test_task.search.domain.api

import com.example.test_task.search.domain.model.Resource
import com.example.test_task.search.domain.model.BinInfoCard
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getInfo(bin: String): Flow<Resource<BinInfoCard>>

}