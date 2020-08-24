package com.kotlin.newtesttask.ui.user

import androidx.lifecycle.LiveData
import com.kotlin.newtesttask.data.repository.MainRepository
import com.kotlin.newtesttask.di.base.BaseViewModel
import com.kotlin.newtesttask.networking.model.UserModel
import javax.inject.Inject

class UserViewModel : BaseViewModel() {

    @Inject
    lateinit var mainRepository: MainRepository

    fun getUsers(): LiveData<List<UserModel>> =
        mainRepository.getUsers()

}