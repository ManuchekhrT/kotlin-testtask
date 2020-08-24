package com.kotlin.newtesttask.di.injector

import com.kotlin.newtesttask.di.modules.RepositoryModule
import com.kotlin.newtesttask.ui.user.UserViewModel
import com.kotlin.newtesttask.ui.user.details.UserDetailsViewModel
import dagger.Component
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(RepositoryModule::class)])
interface ViewModelInjector {

    fun inject(userViewModel: UserViewModel)
    fun inject(userDetailsViewModel: UserDetailsViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun repositoryModule(repositoryModule: RepositoryModule): Builder
    }
}