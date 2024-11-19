package com.example.test_task.data.network

import com.example.test_task.data.dto.requests.BinInfoRequest
import com.example.test_task.data.dto.NetworkResponse

interface NetworkClient {
    suspend fun getInfoCard(dto: BinInfoRequest) : NetworkResponse
}