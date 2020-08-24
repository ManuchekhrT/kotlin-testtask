package com.kotlin.newtesttask.di.modules

import com.kotlin.newtesttask.data.repository.MainRepository
import dagger.Module
import dagger.Provides

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object RepositoryModule {

    @Provides
    internal fun provideMainRepository(): MainRepository {
        return MainRepository()
    }

}