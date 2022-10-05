package com.empedocles.travelapp.domain.repository

import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.util.Resource

interface AllTravelItemRepository {
    suspend fun getAllTravelItem(): Resource<List<TravelModel>>
}