package com.parth8199.fetchrewardsexercise

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parth8199.fetchrewardsexercise.domain.models.ItemList
import com.parth8199.fetchrewardsexercise.network.ItemListCache
import com.parth8199.fetchrewardsexercise.network.response.GetListFetchRewards
import kotlinx.coroutines.launch

private const val TAG = "SharedViewModel"

class SharedViewModel : ViewModel() {
    //Attach SharedRepository
    private val repository = SharedRepository()
    private val _itemByIdLiveData = MutableLiveData<ItemList?>()
    val itemByIdLiveData: LiveData<ItemList?> = _itemByIdLiveData

    fun refreshItem() {
        val cachedItemList = ItemListCache.itemListCache

        //If ItemList is Cached, post that and return to prevent making network call
        if (cachedItemList.isNotEmpty()) {
            _itemByIdLiveData.postValue(cachedItemList)
            return
        }
        viewModelScope.launch {
            //Fetch Item from Repository by making network call
            val response = repository.getItemById()
            _itemByIdLiveData.postValue(response)
            Log.i(TAG, "Made Network Request")
            //save ItemList to Cache
            if (response != null) {
                ItemListCache.itemListCache = response
            }
        }
    }
}