package com.empedocles.travelapp.domain.usecase

import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.repository.SingleTravelItemRepository
import com.empedocles.travelapp.util.Resource
import javax.inject.Inject


class SingleTravelItemUseCase @Inject constructor(
    private val repository: SingleTravelItemRepository
) {

    suspend operator fun invoke(
        id: String
    ): Resource<TravelModel> {
        return repository.getSingleTravelItem(id)
    }
}