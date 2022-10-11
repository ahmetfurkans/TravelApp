package com.empedocles.travelapp.presentation.guide

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.usecase.AllTravelItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(
    private val allTravelItemUseCase: AllTravelItemUseCase
) : ViewModel() {
    fun loadAllTravelItem(
    ) : LiveData<List<TravelModel>> {
        allTravelItemUseCase.apply {
            getAllTravelItem()
            return allTravelList
        }
    }
}