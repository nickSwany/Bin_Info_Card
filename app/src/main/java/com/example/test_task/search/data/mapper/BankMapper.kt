package com.example.test_task.search.data.mapper

import com.example.test_task.data.dto.response.BankDTO
import com.example.test_task.search.domain.model.Bank

class BankMapper {

    fun map(dto: BankDTO?): Bank {
        return Bank(
            city = dto?.city ?: EMPTY_STRING,
            name = dto?.name ?: EMPTY_STRING,
            phone = dto?.phone ?: EMPTY_STRING,
            url = dto?.url ?: EMPTY_STRING
        )
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}