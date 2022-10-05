package com.empedocles.travelapp.data.repository

import com.empedocles.travelapp.data.remote.TravelApiService
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.repository.AllTravelItemRepository
import com.empedocles.travelapp.util.Resource

class AllTravelItemRepositoryImpl(private val apiService: TravelApiService) :
    AllTravelItemRepository, BaseRepositoryImpl() {
    override suspend fun getAllTravelItem(): Resource<List<TravelModel>> {
        return safeApiCall { apiService.getAllTravelList() }
    }
}