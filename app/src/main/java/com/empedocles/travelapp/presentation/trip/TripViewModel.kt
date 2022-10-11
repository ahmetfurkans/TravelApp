package com.empedocles.travelapp.presentation.trip

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.empedocles.travelapp.data.local.TripEntity
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.usecase.AllTravelItemFromRoomUseCase
import com.empedocles.travelapp.domain.usecase.AllTravelItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    private val allTravelItemUseCase: AllTravelItemUseCase,
    private val allTravelItemFromRoomUseCase: AllTravelItemFromRoomUseCase
) : ViewModel() {

    fun loadAllTravelItem(
    ) : LiveData<List<TravelModel>> {
        allTravelItemUseCase.apply {
            getAllTravelItem()
            return allTravelList
        }
    }

    fun loadAllTravelItemFromRoom(
    ) : LiveData<List<TripEntity>> {
        allTravelItemFromRoomUseCase.apply {
            getAllTravelItem()
            return allTravelList
        }
    }
}