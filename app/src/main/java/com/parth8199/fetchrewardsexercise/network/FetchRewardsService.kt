package com.parth8199.fetchrewardsexercise.network

import com.parth8199.fetchrewardsexercise.network.response.GetListFetchRewards
import retrofit2.Response
import retrofit2.http.GET

interface FetchRewardsService {
    @GET("hiring.json")
    suspend fun getItemById(): Response<GetListFetchRewards>
}