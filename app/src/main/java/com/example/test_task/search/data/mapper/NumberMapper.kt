package com.example.test_task.search.data.mapper

import com.example.test_task.data.dto.response.NumberDTO
import com.example.test_task.search.domain.model.Number

class NumberMapper {

    fun map(dto: NumberDTO?): Number {
        return Number(
            length = dto?.length ?: 0,
            luhn = dto?.luhn ?: false
        )
    }
}