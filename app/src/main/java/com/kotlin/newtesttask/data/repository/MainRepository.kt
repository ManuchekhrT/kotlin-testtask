package com.kotlin.newtesttask.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.newtesttask.data.db.UserDao
import com.kotlin.newtesttask.di.base.BaseRepository
import com.kotlin.newtesttask.networking.api.MainApi
import com.kotlin.newtesttask.networking.model.UserModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository : BaseRepository() {

    @Inject
    lateinit var api: MainApi

    @Inject
    lateinit var userDao: UserDao

    private var executor: Executor = Executors.newSingleThreadExecutor()

    fun getUsers(): LiveData<List<UserModel>> {
        val usersLiveData = MutableLiveData<List<UserModel>>()

        GlobalScope.launch {
            //check whether local db is empty and then request for data
            userDao.getUsers().let {
                if (it.isNullOrEmpty()) {
                    val response = api.getUsersAsync().await()
                    response.body()?.let { response ->
                        userDao.insertUsers(response.data)
                    }
                    usersLiveData.postValue(response.body()?.data)
                } else {
                    usersLiveData.postValue(it)
                }
            }
        }

        return usersLiveData
    }

    fun updateUser(user: UserModel) {
        executor.execute {
            userDao.updateUser(user)
        }
    }

    fun deleteUser(user: UserModel) {
        executor.execute {
            userDao.deleteUser(user)
        }
    }

}