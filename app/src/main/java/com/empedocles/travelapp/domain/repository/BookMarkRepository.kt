package com.empedocles.travelapp.domain.repository

import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.util.Resource

interface BookMarkRepository {
    suspend fun addBookMark(id: String, isBookMark: Boolean): Resource<TravelModel>
}