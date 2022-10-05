package com.empedocles.travelapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookMarkDao {

    @Query("SELECT * FROM bookmarkidentity")
    suspend fun getAll(): List<BookMarkIdEntity>

    @Query("DELETE FROM bookmarkidentity WHERE id = :id")
    suspend fun deleteBookMarkId(id: String)

    @Insert
    suspend fun insertBookMarkId(bookMarkIdEntity: BookMarkIdEntity)
}