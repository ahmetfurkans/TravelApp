package com.empedocles.travelapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TripEntity(
    val id: String,
    val city : String,
    val imageSize: Int,
    val url : String,
    val startDate: Long,
    val endDate : Long
){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}
