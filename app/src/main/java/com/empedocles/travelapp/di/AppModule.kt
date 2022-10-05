package com.empedocles.travelapp.di

import android.app.Application
import androidx.room.Room
import com.empedocles.travelapp.data.local.BookMarkDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideBookMarkDatabase(app: Application): BookMarkDataBase{
        return Room.databaseBuilder(
            app,
            BookMarkDataBase::class.java,
            "bookmark.db"
        ).build()
    }

}