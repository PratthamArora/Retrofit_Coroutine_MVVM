package com.pratthamarora.retrofit_coroutine_mvvm.model


import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("age")
    val age: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?

)