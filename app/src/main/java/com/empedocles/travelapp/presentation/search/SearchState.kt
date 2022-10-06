package com.empedocles.travelapp.presentation.search

import com.empedocles.travelapp.domain.model.TravelModel

data class SearchState(
    var isLoading: Boolean = true,
    var isError: Boolean = false,
    var isSearching : Boolean = false,
    var searchQuery : String = "",
    var allTravelItem : List<TravelModel> = emptyList(),
    var nearby : List<TravelModel> = emptyList(),
    var topDestination : List<TravelModel> = emptyList(),
)
