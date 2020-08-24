package com.kotlin.newtesttask.networking.api


import com.kotlin.newtesttask.extensions.CONST_PAGE
import com.kotlin.newtesttask.extensions.PAGE
import com.kotlin.newtesttask.extensions.TEST_API
import com.kotlin.newtesttask.networking.model.ResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET(TEST_API)
    fun getUsersAsync(@Query(CONST_PAGE) page: Int = PAGE): Deferred<Response<ResponseModel>>

}
