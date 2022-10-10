package com.empedocles.travelapp.domain.usecase

import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.repository.SearchRepository
import com.empedocles.travelapp.util.Resource
import javax.inject.Inject

class SearchAllTravelUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(searchQuery: String): Resource<List<TravelModel>> {
        return searchRepository.searchAllTravelList(searchQuery)
    }
}