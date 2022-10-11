package com.empedocles.travelapp.presentation.guide

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.usecase.AllTravelItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(
    private val allTravelItemUseCase: AllTravelItemUseCase
) : ViewModel() {
    private val _pageState = MutableLiveData<GuideState>(GuideState())
    val pageState: LiveData<GuideState> = _pageState

    fun loadAllTravelItem(
    ) : LiveData<List<TravelModel>> {
        allTravelItemUseCase.apply {
            getAllTravelItem()
            return allTravelList
        }
    }
}