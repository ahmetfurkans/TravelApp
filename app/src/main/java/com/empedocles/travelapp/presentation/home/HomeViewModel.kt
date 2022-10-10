package com.empedocles.travelapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.usecase.AllTravelItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val allTravelItemUseCase: AllTravelItemUseCase,
) : ViewModel() {

    private val _pageState = MutableLiveData<HomeState>(HomeState())
    val pageState: LiveData<HomeState> = _pageState

    fun loadAllTravelItem(
    ): LiveData<List<TravelModel>> {
        allTravelItemUseCase.apply {
            getAllTravelItem()
            return allTravelList
        }
    }
}