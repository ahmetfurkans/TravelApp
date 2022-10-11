package com.empedocles.travelapp.di

import android.app.Application
import androidx.room.Room
import com.empedocles.travelapp.data.local.TripDatabase
import com.empedocles.travelapp.data.remote.TravelApiService
import com.empedocles.travelapp.data.repository.AllTravelItemRepositoryImpl
import com.empedocles.travelapp.data.repository.BookMarkRepositoryImpl
import com.empedocles.travelapp.data.repository.SearchRepositoryImpl
import com.empedocles.travelapp.data.repository.SingleTravelItemRepositoryImpl
import com.empedocles.travelapp.domain.repository.AllTravelItemRepository
import com.empedocles.travelapp.domain.repository.BookMarkRepository
import com.empedocles.travelapp.domain.repository.SearchRepository
import com.empedocles.travelapp.domain.repository.SingleTravelItemRepository
import com.empedocles.travelapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGsonConvertor(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideTripDatabase(app: Application): TripDatabase {
        return Room.databaseBuilder(
            app,
            TripDatabase::class.java,
            "trip.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): TravelApiService {
        return retrofit.create(TravelApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAllTravelItemRepository(service: TravelApiService): AllTravelItemRepository {
        return AllTravelItemRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideSingleTravelItemRepository(service: TravelApiService): SingleTravelItemRepository {
        return SingleTravelItemRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideBookMarkRepository(service: TravelApiService): BookMarkRepository {
        return BookMarkRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(service: TravelApiService) : SearchRepository{
        return SearchRepositoryImpl(service)
    }

}