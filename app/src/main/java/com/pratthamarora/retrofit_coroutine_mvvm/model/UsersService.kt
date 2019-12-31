package com.pratthamarora.retrofit_coroutine_mvvm.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UsersService {
    private const val BASE_URL = "http://demo8716682.mockable.io"

    fun getUsersService(): UsersApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersApi::class.java)
    }

}