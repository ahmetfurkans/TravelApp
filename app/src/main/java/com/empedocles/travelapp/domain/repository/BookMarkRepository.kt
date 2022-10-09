package com.empedocles.travelapp.domain.repository

import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.util.Resource
import okhttp3.RequestBody

interface BookMarkRepository  {
    suspend fun addBookMark(id: String, isBookMark: RequestBody): Resource<TravelModel>}