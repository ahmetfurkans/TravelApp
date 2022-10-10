package com.empedocles.travelapp.presentation.search_result

import com.empedocles.travelapp.domain.model.TravelModel

data class SearchResultState(
    var isLoading: Boolean = true,
    var isError: Boolean = false,
    var searchResults : List<TravelModel> = listOf<TravelModel>()
)