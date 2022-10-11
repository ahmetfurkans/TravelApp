package com.empedocles.travelapp.data.repository

import com.empedocles.travelapp.data.remote.TravelApiService
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.repository.SingleTravelItemRepository
import com.empedocles.travelapp.util.Resource

class SingleTravelItemRepositoryImpl(
    private val apiService: TravelApiService,
): SingleTravelItemRepository, BaseRepositoryImpl(){

    override suspend fun getSingleTravelItem(id : String): Resource<TravelModel> {
        return safeApiCall { apiService.getTravelItem(id)}
    }
}