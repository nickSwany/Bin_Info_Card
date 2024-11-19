package com.example.test_task.data.network

import com.example.test_task.data.dto.response.BinInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): BinInfoResponse

}