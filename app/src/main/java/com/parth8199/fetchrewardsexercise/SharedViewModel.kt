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
    private val repository = SharedRepository()
    private val _itemByIdLiveData = MutableLiveData<ItemList?>()
    val itemByIdLiveData: LiveData<ItemList?> = _itemByIdLiveData

    fun refreshItem() {
        val cachedItemList = ItemListCache.itemListCache
        if (cachedItemList.isNotEmpty()) {
            _itemByIdLiveData.postValue(cachedItemList)
            return
        }
        viewModelScope.launch {
            val response = repository.getItemById()
            _itemByIdLiveData.postValue(response)
            Log.i(TAG, "Made Network Request")
            if (response != null) {
                ItemListCache.itemListCache=response
            }
        }
    }
}