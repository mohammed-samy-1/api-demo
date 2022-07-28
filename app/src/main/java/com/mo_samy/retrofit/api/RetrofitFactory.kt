package com.mo_samy.retrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    fun productApi():ProductAPI{
        val calls =
            Retrofit.Builder()
                .baseUrl("https://android-training.appssquare.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductAPI::class.java)
        return calls
    }
}