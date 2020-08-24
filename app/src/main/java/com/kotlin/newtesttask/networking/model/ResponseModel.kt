package com.kotlin.newtesttask.networking.model

import com.google.gson.annotations.SerializedName
import com.kotlin.newtesttask.extensions.CONST_DATA

data class ResponseModel(
    @SerializedName(CONST_DATA) var data: List<UserModel>
)