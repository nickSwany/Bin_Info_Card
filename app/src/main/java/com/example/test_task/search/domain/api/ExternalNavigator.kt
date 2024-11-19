package com.example.test_task.search.domain.api

interface ExternalNavigator {
    fun emailTo(email: String)
    fun callTo(phone: String)
}