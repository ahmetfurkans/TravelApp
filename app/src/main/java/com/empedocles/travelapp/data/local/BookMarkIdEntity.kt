package com.empedocles.travelapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookMarkIdEntity(
    @PrimaryKey val uuid: Int,
    val id: String
)
