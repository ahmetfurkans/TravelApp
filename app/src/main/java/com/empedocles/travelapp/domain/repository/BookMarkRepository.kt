package com.empedocles.travelapp.domain.repository

import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.util.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part

interface BookMarkRepository  {
    suspend fun addBookMark(id: String, isBookMark: Boolean): Resource<TravelModel>}