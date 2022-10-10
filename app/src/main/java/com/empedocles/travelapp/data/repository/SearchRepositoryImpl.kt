package com.empedocles.travelapp.data.repository

import com.empedocles.travelapp.data.remote.TravelApiService
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.repository.AllTravelItemRepository
import com.empedocles.travelapp.domain.repository.SearchRepository
import com.empedocles.travelapp.util.Resource

class SearchRepositoryImpl(
    private val apiService: TravelApiService
) :
    SearchRepository, BaseRepositoryImpl() {
    override suspend fun searchAllTravelList(searchQuery: String): Resource<List<TravelModel>> {
        return safeApiCall { apiService.searchAllTravelList(searchQuery) }
    }
}