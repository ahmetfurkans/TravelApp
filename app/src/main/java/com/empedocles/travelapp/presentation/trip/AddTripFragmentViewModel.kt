package com.empedocles.travelapp.presentation.trip

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empedocles.travelapp.data.local.TripDatabase
import com.empedocles.travelapp.data.local.TripEntity
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.usecase.AllTravelItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddTripFragmentViewModel @Inject constructor(
    private val allTravelItemUseCase: AllTravelItemUseCase,
    private val tripDatabase: TripDatabase
) : ViewModel() {

    fun addTrip(entity: TripEntity){
        viewModelScope.launch {
            val date = Date().time
            tripDatabase.dao.insertTripModel(
                entity
            )
        }
    }

    fun loadAllTravelItem(
    ) : LiveData<List<TravelModel>> {
        allTravelItemUseCase.apply {
            getAllTravelItem()
            return allTravelList
        }
    }
}