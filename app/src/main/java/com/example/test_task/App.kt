package com.example.test_task

import android.app.Application
import com.example.test_task.di.dataModule
import com.example.test_task.di.interactorModule
import com.example.test_task.di.repositoryModule
import com.example.test_task.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    dataModule,
                    interactorModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

}