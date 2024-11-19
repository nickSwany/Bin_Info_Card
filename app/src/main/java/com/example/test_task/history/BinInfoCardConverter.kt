package com.example.test_task.history

import com.example.test_task.db.entity.SearchHistoryInfoEntity
import com.example.test_task.search.domain.model.Bank
import com.example.test_task.search.domain.model.BinInfoCard
import com.example.test_task.search.domain.model.Country
import com.example.test_task.search.domain.model.Number


class BinInfoCardConverter {

    fun map(card: BinInfoCard): SearchHistoryInfoEntity = SearchHistoryInfoEntity(
        id = 0,
        brand = card.brand,
        scheme = card.scheme,
        type = card.type,
        prepaid = card.prepaid,
        bankName = card.bank.name,
        bankCity = card.bank.city,
        bankPhone = card.bank.phone,
        bankUrl = card.bank.url,
        countryName = card.country.name,
        countryAlpha2 = card.country.alpha2,
        countryCurrency = card.country.currency,
        countryEmoji = card.country.emoji,
        countryLatitude = card.country.latitude,
        countryLongitude = card.country.longitude,
        countryNumeric = card.country.numeric,
        numberLength = card.number.length,
        numberLuhn = card.number.luhn
    )

    fun map(card: SearchHistoryInfoEntity): BinInfoCard = BinInfoCard(
        bank = Bank(
            name = card.bankName.toString(),
            url = card.bankUrl.toString(),
            phone = card.bankPhone.toString(),
            city = ""
        ),
        brand = card.brand.toString(),
        country = Country(
            alpha2 = "",
            currency = "",
            emoji = "",
            latitude = card.countryLatitude,
            longitude = card.countryLongitude,
            name = card.countryName,
            numeric = ""
        ),
        number = Number(
            length = card.numberLength,
            luhn = card.numberLuhn
        ),
        prepaid = card.prepaid == true,
        scheme = card.scheme.toString(),
        type = card.type.toString()
    )


}
