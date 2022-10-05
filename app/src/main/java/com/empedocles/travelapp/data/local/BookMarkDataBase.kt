package com.empedocles.travelapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BookMarkIdEntity::class],
    version = 1
)
abstract class BookMarkDataBase : RoomDatabase(){
    abstract val dao : BookMarkDao
}