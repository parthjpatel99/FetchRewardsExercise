package com.parth8199.fetchrewardsexercise

import com.parth8199.fetchrewardsexercise.domain.mapper.ItemMapper
import com.parth8199.fetchrewardsexercise.domain.models.ItemList
import com.parth8199.fetchrewardsexercise.network.NetworkLayer

class SharedRepository {
    suspend fun getItemById(): ItemList?{
        //Make network request by calling getItemById() from NetworkLayer.apiClient
        val request = NetworkLayer.apiClient.getItemById()
        //Failed To make request
        if (request.failed){
            return null
        }
        if (!request.isSuccessful){
            return null
        }
        //Return Domain Layer ItemList(ArrayList of AnItem object)
        return ItemMapper.buidlFrom(response = request.body)
    }
}