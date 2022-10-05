package com.empedocles.travelapp.domain.repository

import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.util.Resource

interface SingleTravelItemRepository {
    suspend fun getSingleTravelItem(id: String): Resource<TravelModel>
}