package com.empedocles.travelapp.presentation.home

import androidx.annotation.DrawableRes
import com.empedocles.travelapp.R

class HomeButtonConstants {
    companion object {
        val FLIGHTS = ButtonModel(R.drawable.ic_plane, "Flights")
        val HOTELS = ButtonModel(R.drawable.ic_hotel, "Hotels")
        val CARS = ButtonModel(R.drawable.ic_car, "Cars")
        val TAXI = ButtonModel(R.drawable.ic_taxi, "Taxi")
    }
}

data class ButtonModel(
    @DrawableRes val id: Int,
    val text: String
)
