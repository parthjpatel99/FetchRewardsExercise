package com.parth8199.fetchrewardsexercise.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkLayer {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val fetchRewardsService: FetchRewardsService by lazy {
        retrofit.create(FetchRewardsService::class.java)
    }

    val apiClient = ApiClient(fetchRewardsService)
}