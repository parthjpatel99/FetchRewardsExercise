package com.parth8199.fetchrewardsexercise.domain.mapper

import com.parth8199.fetchrewardsexercise.domain.models.AnItem
import com.parth8199.fetchrewardsexercise.domain.models.ItemList
import com.parth8199.fetchrewardsexercise.network.response.GetListFetchRewards
//The sorting and Name notNull logic is handled here mapping from network layer data to domain layer data
object ItemMapper {
    fun buidlFrom(response:GetListFetchRewards): ItemList {
        val list = ItemList()
        for (item in response){
            if (item.name.isNullOrEmpty()){
                continue
            }
            list.add(AnItem(
                id = item.id,
                listId = item.listId,
                name = item.name
            ))
        }
        list.sortWith(compareBy<AnItem> { it.listId }.thenBy { it.id })
        return list
    }
}