package com.empedocles.travelapp.presentation.trip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empedocles.travelapp.data.local.TripDatabase
import com.empedocles.travelapp.data.local.TripEntity
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.usecase.AllTravelItemFromRoomUseCase
import com.empedocles.travelapp.domain.usecase.AllTravelItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    private val allTravelItemUseCase: AllTravelItemUseCase,
    private val allTravelItemFromRoomUseCase: AllTravelItemFromRoomUseCase
) : ViewModel() {

    private val _pageState = MutableLiveData<TripState>(TripState())
    val pageState: LiveData<TripState> = _pageState

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