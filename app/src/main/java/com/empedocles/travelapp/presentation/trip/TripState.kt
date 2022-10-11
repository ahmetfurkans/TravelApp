package com.empedocles.travelapp.presentation.trip

import com.empedocles.travelapp.data.local.TripEntity
import com.empedocles.travelapp.domain.model.TravelModel

data class TripState(
    var isLoading: Boolean = true,
    var isError: Boolean = false,
    var allTravelItem : List<TravelModel> = emptyList(),
    var trip : List<TripEntity> = emptyList(),
    var bookmark : List<TravelModel> = emptyList()
)

