package com.empedocles.travelapp.data.remote

import com.empedocles.travelapp.domain.model.TravelModel
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*


interface TravelApiService {
    @GET("AllTravelList")
    suspend fun getAllTravelList(): Response<List<TravelModel>>

    @GET("AllTravelList/{id}")
    suspend fun getTravelItem(@Path("id") id: String): Response<TravelModel>

    @PUT("AllTravelList/{id}")
    suspend fun putTravelItem(
        @Path("id") id: String,
        @Part("isBookmark") isBookmark: RequestBody
    ): Response<TravelModel>
}