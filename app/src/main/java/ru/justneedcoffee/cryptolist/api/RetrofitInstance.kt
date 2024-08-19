package ru.justneedcoffee.cryptolist.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.justneedcoffee.cryptolist.utils.BASE_URL

object RetrofitInstance {
    private var INSTANCE: Retrofit? = null

    fun getInstance(): Retrofit = INSTANCE ?: kotlin.run {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}