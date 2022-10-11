package com.empedocles.travelapp.data.repository

import com.empedocles.travelapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepositoryImpl {

    // Function used in all Repos to handle api errors
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
        val customErrorMessage = "Something went wrong"

        // Returning api response
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful) {
                    Resource.Success(data = response.body())
                } else {
                    Resource.Error(customErrorMessage)
                }

            } catch (e: HttpException) {
                Resource.Error(e.message ?: customErrorMessage)
            } catch (e: IOException) {
                Resource.Error("Please check your network connection")
            } catch (e: Exception) {
                Resource.Error(errorMessage = customErrorMessage)
            }
        }
    }
}