package com.kotlin.newtesttask.di.injector

import com.kotlin.newtesttask.data.repository.MainRepository
import com.kotlin.newtesttask.di.modules.DatabaseModule
import com.kotlin.newtesttask.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class), (DatabaseModule::class)])
interface RepositoryInjector {

    fun inject(mainRepository: MainRepository)

    @Component.Builder
    interface Builder {
        fun build(): RepositoryInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun databaseModule(databaseModule: DatabaseModule): Builder
    }
}