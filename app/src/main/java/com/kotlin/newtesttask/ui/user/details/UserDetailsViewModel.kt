package com.kotlin.newtesttask.ui.user.details

import com.kotlin.newtesttask.data.repository.MainRepository
import com.kotlin.newtesttask.di.base.BaseViewModel
import com.kotlin.newtesttask.networking.model.UserModel
import javax.inject.Inject

class UserDetailsViewModel : BaseViewModel() {

    @Inject
    lateinit var mainRepository: MainRepository

    fun updateUser(user: UserModel) {
        mainRepository.updateUser(user)
    }

    fun deleteUser(user: UserModel) {
        mainRepository.deleteUser(user)
    }


}