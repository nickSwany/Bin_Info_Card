package com.example.test_task.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.test_task.data.dto.NetworkResponse
import com.example.test_task.data.dto.requests.BinInfoRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class RetrofitNetworkClient(
    private val apiService: ApiService,
    private val context: Context
) : NetworkClient {


    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        return false
    }

    override suspend fun getInfoCard(dto: BinInfoRequest): NetworkResponse =
        if (!isConnected()) {
            NetworkResponse().apply { resultCode = ERROR_NO_INTERNET }
        } else {
            withContext(Dispatchers.IO) {
                try {
                    apiService.getBinInfo(dto.bin).apply {
                        resultCode =
                            SUCCESS_STATUS
                    }
                } catch (e: HttpException) {
                    NetworkResponse().apply { resultCode = e.code() }
                } catch (e: IOException) {
                    NetworkResponse().apply { resultCode = IO_EXCEPTION }
                }
            }
        }

    companion object {
        const val ERROR_NO_INTERNET = -1
        const val IO_EXCEPTION = -2
        const val SUCCESS_STATUS = 200
    }

}