package com.pratthamarora.retrofit_coroutine_mvvm.model

import retrofit2.Response
import retrofit2.http.GET

interface UsersApi {
    @GET("cardData") //endpoint
    suspend fun getUsers(): Response<List<Users>>
}