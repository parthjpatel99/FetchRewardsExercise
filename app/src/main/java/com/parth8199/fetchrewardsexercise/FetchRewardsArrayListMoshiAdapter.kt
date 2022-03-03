package com.parth8199.fetchrewardsexercise

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class FetchRewardsArrayListMoshiAdapter {
    @ToJson
    fun arrayListToJson(list: ArrayList<GetListFetchRewardsItem>): List<GetListFetchRewardsItem> = list

    @FromJson
    fun arrayListFromJson(list: List<GetListFetchRewardsItem>): ArrayList<GetListFetchRewardsItem> = ArrayList(list)
}