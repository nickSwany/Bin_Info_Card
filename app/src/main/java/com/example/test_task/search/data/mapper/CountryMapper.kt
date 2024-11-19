package com.example.test_task.search.data.mapper

import com.example.test_task.data.dto.response.CountryDTO
import com.example.test_task.search.domain.model.Country

class CountryMapper {

    fun map(dto: CountryDTO?): Country {
        return Country(
            alpha2 = dto?.alpha2 ?: EMPTY_STRING,
            currency = dto?.currency ?: EMPTY_STRING,
            emoji = dto?.emoji ?: EMPTY_STRING,
            latitude = dto?.latitude ?: EMPTY_INTEGER,
            longitude = dto?.longitude ?: EMPTY_INTEGER,
            name = dto?.name ?: EMPTY_STRING,
            numeric = dto?.numeric ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val EMPTY_INTEGER = 0
    }
}