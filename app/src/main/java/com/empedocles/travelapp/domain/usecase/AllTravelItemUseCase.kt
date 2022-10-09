package com.empedocles.travelapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.repository.AllTravelItemRepository
import com.empedocles.travelapp.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllTravelItemUseCase @Inject constructor(
    private val repository: AllTravelItemRepository
) {
    private var _allTravelList = MutableLiveData<List<TravelModel>>()
    val allTravelList: LiveData<List<TravelModel>> = _allTravelList

    suspend operator fun invoke(
    ): Resource<List<TravelModel>> {
        return repository.getAllTravelItem()
    }

    fun getAllTravelItem(
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            when (val result = repository.getAllTravelItem()) {
                is Resource.Error -> {
                    println("Error")
                }
                is Resource.Loading -> {
                    println("Loading")
                }
                is Resource.Success -> {
                    result.data?.let { list ->
                        _allTravelList.postValue(list)                     }
                }
            }
        }
    }
}
