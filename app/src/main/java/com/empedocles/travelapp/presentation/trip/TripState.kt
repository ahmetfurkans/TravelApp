package com.empedocles.travelapp.presentation.trip

import com.empedocles.travelapp.domain.model.TravelModel

data class TripState(
    var isLoading: Boolean = true,
    var isError: Boolean = false,
    var allTravelItem : List<TravelModel> = emptyList(),
    var trip : List<TravelModel> = emptyList(),
    var bookmark : List<TravelModel> = emptyList()
)

