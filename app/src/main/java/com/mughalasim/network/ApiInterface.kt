package com.mughalasim.network

import com.mughalasim.network.model.ResultModel
import com.mughalasim.utils.Shared.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("a7abf1f6-b3d5-4dea-89f4-5141ff264bfa/6ln63lARRtIkiJF0LiyvQTBiKBtLmHscrT71Or3Mmc4wb85l8J/en/feed/d040fcda-0a39-4c25-b50e-2808f3308bde/sections/")
    fun getResult(): Call<ResultModel>

    companion object Factory {
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java);
        }
    }
}