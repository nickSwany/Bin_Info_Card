package com.example.test_task.search.domain.impl

import android.util.Log
import com.example.test_task.search.domain.model.Resource
import com.example.test_task.data.dto.requests.BinInfoRequest
import com.example.test_task.data.dto.response.BinInfoResponse
import com.example.test_task.data.network.NetworkClient
import com.example.test_task.search.data.mapper.ResponseToInfoMapper
import com.example.test_task.search.domain.api.SearchRepository
import com.example.test_task.search.domain.model.BinInfoCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: ResponseToInfoMapper
) : SearchRepository {

    override fun getInfo(bin: String): Flow<Resource<BinInfoCard>> = flow {
        val binRequest = BinInfoRequest(bin)

        val response = try {
            networkClient.getInfoCard(binRequest)
        } catch (e: Exception) {
            emit(Resource.Error(null))
            return@flow
        }

        when (response.resultCode) {
            ERROR_NO_INTERNET -> {
                emit(Resource.Error(ERROR_NO_INTERNET))
            }

            IO_EXCEPTION -> {
                emit(Resource.Error(IO_EXCEPTION))
            }

            SUCCESS_STATUS -> {
                if (response is BinInfoResponse) {
                    val result = try {
                        mapper.map(response)
                    } catch (e: Exception) {
                        emit(Resource.Error(NOTHING_FOUND))
                        return@flow
                    }
                    emit(Resource.Success(result))
                } else {
                    emit(Resource.Error(NOTHING_FOUND))
                }
            }
            429 -> {
                delay(RETRY_DELAY)
                emit(getInfo(bin).first())
            }

            else -> {
                emit(Resource.Error(response.resultCode))
            }
        }
    }.flowOn(Dispatchers.IO)



    companion object {
        private const val ERROR_NO_INTERNET = -1
        private const val IO_EXCEPTION = -2
        private const val SUCCESS_STATUS = 200
        private const val NOTHING_FOUND = 404
        private const val RETRY_DELAY = 2000L
    }
}