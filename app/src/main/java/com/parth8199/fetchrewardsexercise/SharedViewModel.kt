package com.parth8199.fetchrewardsexercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parth8199.fetchrewardsexercise.domain.models.ItemList
import com.parth8199.fetchrewardsexercise.network.response.GetListFetchRewards
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val repository = SharedRepository()
    private val _itemByIdLiveData = MutableLiveData<ItemList?>()
    val itemByIdLiveData: LiveData<ItemList?> = _itemByIdLiveData

    fun refreshItem() {
        viewModelScope.launch {
            val response = repository.getItemById()
            _itemByIdLiveData.postValue(response)
        }
    }
}