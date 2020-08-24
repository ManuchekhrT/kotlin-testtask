package com.kotlin.newtesttask.networking.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.kotlin.newtesttask.extensions.*
import java.io.Serializable

@Entity(tableName = CONST_USER)
data class UserModel(
    @PrimaryKey(autoGenerate = true) @SerializedName(CONST_ID) var id: Int,
    @ColumnInfo(name = CONST_EMAIL) @SerializedName(CONST_EMAIL) var email: String,
    @ColumnInfo(name = CONST_FIRST_NAME) @SerializedName(CONST_FIRST_NAME) var first_name: String,
    @ColumnInfo(name = CONST_LAST_NAME) @SerializedName(CONST_LAST_NAME) var last_name: String,
    @ColumnInfo(name = CONST_AVATAR) @SerializedName(CONST_AVATAR) var avatar: String
): Serializable