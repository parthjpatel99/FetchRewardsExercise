package com.parth8199.fetchrewardsexercise

import retrofit2.Call
import retrofit2.http.GET

interface FetchRewardsService {
    @GET("hiring.json")
    fun getItemById(): Call<Any>
}