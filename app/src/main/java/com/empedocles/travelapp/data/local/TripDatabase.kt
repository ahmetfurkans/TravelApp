package com.empedocles.travelapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TripEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TripDatabase : RoomDatabase() {
    abstract val dao: TripDao
}
