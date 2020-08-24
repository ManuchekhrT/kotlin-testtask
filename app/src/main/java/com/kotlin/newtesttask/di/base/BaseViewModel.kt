package com.kotlin.newtesttask.di.base

import androidx.lifecycle.ViewModel
import com.kotlin.newtesttask.di.injector.DaggerViewModelInjector
import com.kotlin.newtesttask.di.injector.ViewModelInjector
import com.kotlin.newtesttask.di.modules.RepositoryModule
import com.kotlin.newtesttask.ui.user.UserViewModel
import com.kotlin.newtesttask.ui.user.details.UserDetailsViewModel


abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector.builder()
        .repositoryModule(RepositoryModule)
        .build()

    init {
        inject()
    }


    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is UserViewModel -> injector.inject(this)
            is UserDetailsViewModel -> injector.inject(this)
        }
    }

}