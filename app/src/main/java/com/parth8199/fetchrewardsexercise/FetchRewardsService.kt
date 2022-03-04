package com.parth8199.fetchrewardsexercise

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface FetchRewardsService {
    @GET("hiring.json")
   suspend fun getItemById(): Response<GetListFetchRewards>
}