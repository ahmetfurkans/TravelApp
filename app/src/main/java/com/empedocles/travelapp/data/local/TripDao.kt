package com.empedocles.travelapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TripDao {
    @Query("SELECT * FROM tripentity")
    suspend fun getAll(): List<TripEntity>

    @Insert
    suspend fun insertTripModel(tripEntity: TripEntity)
}