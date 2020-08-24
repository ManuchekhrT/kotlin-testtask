package com.kotlin.newtesttask.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kotlin.newtesttask.BuildConfig.DEBUG
import com.kotlin.newtesttask.extensions.BASE_TEST_API
import com.kotlin.newtesttask.networking.api.MainApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder =
        Retrofit
            .Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))


    @Provides
    @Singleton
    internal fun provideRetrofit(
        builder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): Retrofit =
        builder
            .client(okHttpClient)
            .baseUrl(BASE_TEST_API)
            .build()


    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideMainApi(retrofit: Retrofit): MainApi =
        retrofit.create(MainApi::class.java)

}