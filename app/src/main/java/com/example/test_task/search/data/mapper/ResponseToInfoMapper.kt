package com.example.test_task.search.data.mapper

import com.example.test_task.data.dto.response.BinInfoResponse
import com.example.test_task.search.domain.model.BinInfoCard

class ResponseToInfoMapper(
    private val bankMapper: BankMapper,
    private val countryMapper: CountryMapper,
    private val numberMapper: NumberMapper,
) {

    fun map(response: BinInfoResponse?): BinInfoCard {
        return BinInfoCard(
            bank = bankMapper.map(response?.bank),
            brand = response?.brand ?: EMPTY_STRING,
            country = countryMapper.map(response?.country),
            number = numberMapper.map(response?.number),
            prepaid = response?.prepaid ?: false,
            scheme = response?.scheme ?: EMPTY_STRING,
            type = response?.type ?: EMPTY_STRING,
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}