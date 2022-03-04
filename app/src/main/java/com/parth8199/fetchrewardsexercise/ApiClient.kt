package com.parth8199.fetchrewardsexercise

import retrofit2.Response

class ApiClient(
    private val fetchRewardsService: FetchRewardsService
) {
    suspend fun getItemById():SimpleResponse<GetListFetchRewards> {
        return safeApiCall { fetchRewardsService.getItemById() }
    }

    private inline fun <T> safeApiCall(apiCall:() -> Response<T>):SimpleResponse<T>{
        return try {
            SimpleResponse.success(apiCall.invoke())
        }catch (e:Exception){
            SimpleResponse.failure(e)
        }
    }
}