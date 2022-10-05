package com.empedocles.travelapp.data.remote

import com.empedocles.travelapp.domain.model.TravelModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TravelApiService{
    @GET("AllTravelList")
    suspend fun getAllTravelList(): Response<List<TravelModel>>

    @GET("AllTravelList/{id}")
    suspend fun getTravelItem(@Path("id") id : String): Response<TravelModel>
}