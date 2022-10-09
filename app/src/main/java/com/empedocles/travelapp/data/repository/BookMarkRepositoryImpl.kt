package com.empedocles.travelapp.data.repository

import com.empedocles.travelapp.data.remote.TravelApiService
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.domain.repository.BookMarkRepository
import com.empedocles.travelapp.domain.repository.SingleTravelItemRepository
import com.empedocles.travelapp.util.Resource
import okhttp3.RequestBody

class BookMarkRepositoryImpl(
    private val apiService: TravelApiService,
) : BookMarkRepository, BaseRepositoryImpl() {

    override suspend fun addBookMark(id: String, isBookMark: RequestBody): Resource<TravelModel> {
        return safeApiCall { apiService.putTravelItem(id, isBookMark) }
    }
}