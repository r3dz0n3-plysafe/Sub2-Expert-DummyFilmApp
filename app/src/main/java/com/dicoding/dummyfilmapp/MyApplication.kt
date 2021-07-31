@file:Suppress("unused")

package com.dicoding.dummyfilmapp

import android.app.Application
import com.dicoding.dummyfilmapp.core.di.databaseModule
import com.dicoding.dummyfilmapp.core.di.networkModule
import com.dicoding.dummyfilmapp.core.di.repositoryModule
import com.dicoding.dummyfilmapp.di.useCaseModule
import com.dicoding.dummyfilmapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}