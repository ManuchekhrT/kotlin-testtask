package com.kotlin.newtesttask.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.newtesttask.networking.model.UserModel

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class MainDb : RoomDatabase() {
    abstract fun userDao(): UserDao
}