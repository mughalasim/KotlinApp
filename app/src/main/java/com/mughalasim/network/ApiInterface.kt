package com.mughalasim.network

import com.mughalasim.network.model.MainDataModel
import com.mughalasim.utils.Shared.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    // Calling the API with the page number
    @GET("hot.json")
    fun getResult(@Query("after") page: String?): Call<MainDataModel>

    companion object Factory {
        // Create the API single instance once and reuse as a companion object
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}