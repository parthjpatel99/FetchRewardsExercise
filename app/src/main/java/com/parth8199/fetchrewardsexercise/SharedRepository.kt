package com.parth8199.fetchrewardsexercise

import com.parth8199.fetchrewardsexercise.domain.mapper.ItemMapper
import com.parth8199.fetchrewardsexercise.domain.models.ItemList
import com.parth8199.fetchrewardsexercise.network.NetworkLayer

class SharedRepository {
    suspend fun getItemById(): ItemList?{
        val request = NetworkLayer.apiClient.getItemById()
        if (request.failed){
            return null
        }
        if (!request.isSuccessful){
            return null
        }
        return ItemMapper.buidlFrom(response = request.body)
    }
}