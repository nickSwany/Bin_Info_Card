package com.example.test_task.data.dto.response

import com.example.test_task.data.dto.NetworkResponse

data class BinInfoResponse(
    val bank: BankDTO?,
    val brand: String?,
    val country: CountryDTO?,
    val number: NumberDTO?,
    val prepaid: Boolean?,
    val scheme: String?,
    val type: String?
) : NetworkResponse()