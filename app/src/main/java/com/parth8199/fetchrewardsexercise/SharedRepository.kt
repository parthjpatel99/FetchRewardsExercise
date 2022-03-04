package com.parth8199.fetchrewardsexercise

class SharedRepository {
    suspend fun getItemById(): GetListFetchRewards?{
        val request = NetworkLayer.apiClient.getItemById()
        if (request.failed){
            return null
        }
        if (!request.isSuccessful){
            return null
        }
        return request.body
    }
}