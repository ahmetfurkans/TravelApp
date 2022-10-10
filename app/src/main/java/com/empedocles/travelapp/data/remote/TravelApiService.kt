package com.empedocles.travelapp.data.remote

import com.empedocles.travelapp.domain.model.TravelModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*


interface TravelApiService {
    @GET("AllTravelList")
    suspend fun getAllTravelList(): Response<List<TravelModel>>

    @GET("AllTravelList/{id}")
    suspend fun getTravelItem(@Path("id") id: String): Response<TravelModel>

    @GET("AllTravelList")
    suspend fun searchAllTravelList(@Query("search") searchQuery: String): Response<List<TravelModel>>

    @FormUrlEncoded
    @PUT("AllTravelList/{id}")
    suspend fun putTravelItem(
        @Path("id") id: String,
        @Field("isBookmark") isBookmark: Boolean
    ): Response<TravelModel>
}