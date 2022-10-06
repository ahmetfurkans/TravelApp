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
    suspend operator fun invoke(
    ): Resource<List<TravelModel>> {
        return repository.getAllTravelItem()
    }
}