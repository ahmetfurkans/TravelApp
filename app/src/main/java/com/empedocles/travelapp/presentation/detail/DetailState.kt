package com.empedocles.travelapp.presentation.detail

import com.empedocles.travelapp.domain.model.TravelModel

data class DetailState(
    var isLoading: Boolean = true,
    var isError: Boolean = false,
    var selectedItem : TravelModel? = null
)