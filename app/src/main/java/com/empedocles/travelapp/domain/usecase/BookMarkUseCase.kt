package com.empedocles.travelapp.domain.usecase

import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.repository.AllTravelItemRepository
import com.empedocles.travelapp.domain.repository.BookMarkRepository
import com.empedocles.travelapp.util.Resource
import okhttp3.RequestBody
import javax.inject.Inject

class BookMarkUseCase @Inject constructor(
    private val repository: BookMarkRepository
) {
    suspend fun changeBookMark(
        id: String,
        isBookmark: RequestBody
    ): Resource<TravelModel> {
        return repository.addBookMark(id, isBookmark)
    }
}