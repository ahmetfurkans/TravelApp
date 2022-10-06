package com.empedocles.travelapp.presentation.guide

import com.empedocles.travelapp.domain.model.TravelModel

data class GuideState(
    var isLoading: Boolean = true,
    var isError: Boolean = false,
    var isSearching : Boolean = false,
    var searchQuery : String = "",
    var allTravelItem : List<TravelModel> = emptyList(),
    var mightneed : List<TravelModel> = emptyList(),
    var toppick : List<TravelModel> = emptyList()
)

