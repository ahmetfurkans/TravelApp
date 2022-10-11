package com.empedocles.travelapp.domain.usecase

import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.repository.BookMarkRepository
import com.empedocles.travelapp.util.Resource
import javax.inject.Inject

class BookMarkUseCase @Inject constructor(
    private val repository: BookMarkRepository,
    private val allTravelItemUseCase: AllTravelItemUseCase
) {

    suspend fun changeBookMark(
        id: String,
        isBookmark: Boolean
    ): Resource<TravelModel> {
        allTravelItemUseCase.allTravelList
        return repository.addBookMark(id, isBookmark)
    }
}