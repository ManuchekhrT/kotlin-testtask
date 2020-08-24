package com.kotlin.newtesttask.di.base

import com.kotlin.newtesttask.MainApplication
import com.kotlin.newtesttask.data.repository.MainRepository
import com.kotlin.newtesttask.di.injector.DaggerRepositoryInjector
import com.kotlin.newtesttask.di.injector.RepositoryInjector
import com.kotlin.newtesttask.di.modules.DatabaseModule
import com.kotlin.newtesttask.di.modules.NetworkModule


abstract class BaseRepository {

    private val injector: RepositoryInjector = DaggerRepositoryInjector.builder()
        .networkModule(NetworkModule)
        .databaseModule(
            DatabaseModule(
                MainApplication.instance
            )
        )
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MainRepository -> injector.inject(this)
        }
    }
}