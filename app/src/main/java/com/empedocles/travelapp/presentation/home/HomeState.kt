package com.empedocles.travelapp.presentation.home

import com.empedocles.travelapp.domain.model.TravelModel

data class HomeState(
    var isLoading: Boolean = true,
    var isError: Boolean = false,
    var allTravelItem : List<TravelModel> = emptyList(),
    var transportation : List<TravelModel> = emptyList(),
    var flights : List<TravelModel> = emptyList(),
    var hotels : List<TravelModel> = emptyList()
)

