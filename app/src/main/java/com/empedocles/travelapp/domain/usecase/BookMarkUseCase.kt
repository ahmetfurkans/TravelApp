package com.empedocles.travelapp.domain.usecase

import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.repository.AllTravelItemRepository
import com.empedocles.travelapp.domain.repository.BookMarkRepository
import com.empedocles.travelapp.util.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.Part
import javax.inject.Inject

class BookMarkUseCase @Inject constructor(
    private val repository: BookMarkRepository
) {
    suspend fun changeBookMark(
        id: String,
        isBookmark: Boolean
    ): Resource<TravelModel> {
        return repository.addBookMark(id, isBookmark)
    }
}