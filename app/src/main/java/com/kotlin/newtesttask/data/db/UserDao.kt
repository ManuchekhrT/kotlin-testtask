package com.kotlin.newtesttask.data.db

import androidx.room.*
import com.kotlin.newtesttask.networking.model.UserModel

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUsers(): List<UserModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserModel>)

    @Delete
    fun deleteUser(vararg user: UserModel)

    @Update
    fun updateUser(vararg user: UserModel)

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Int): UserModel

    @Query("SELECT COUNT(*) FROM user")
    fun getCount(): Int
}