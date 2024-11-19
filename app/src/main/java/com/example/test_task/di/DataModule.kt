package com.example.test_task.di

import androidx.room.Room
import com.example.test_task.data.network.ApiService
import com.example.test_task.data.network.NetworkClient
import com.example.test_task.data.network.RetrofitNetworkClient
import com.example.test_task.db.AppDatabase
import com.example.test_task.search.data.mapper.BankMapper
import com.example.test_task.search.data.mapper.CountryMapper
import com.example.test_task.search.data.mapper.NumberMapper
import com.example.test_task.search.data.mapper.ResponseToInfoMapper
import com.example.test_task.search.domain.api.ExternalNavigator
import com.example.test_task.search.domain.impl.ExternalNavigatorImpl
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single(named("baseUrl")) { "https://lookup.binlist.net/" }

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(get<String>(named("baseUrl")))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)

    }

    factory { Gson() }

    single<NetworkClient> {
        RetrofitNetworkClient(get(), androidContext())
    }

    single<CountryMapper> {
        CountryMapper()
    }

    single<BankMapper> { BankMapper() }
    single<NumberMapper> { NumberMapper() }
    single<ResponseToInfoMapper> {
        ResponseToInfoMapper(get(), get(), get())
    }

    single<AppDatabase> {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().searchHistoryDao() }

    factory<ExternalNavigator> { ExternalNavigatorImpl(androidContext()) }
}