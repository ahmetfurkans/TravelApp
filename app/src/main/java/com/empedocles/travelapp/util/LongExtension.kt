package com.empedocles.travelapp.util

import java.text.SimpleDateFormat

fun Long.toDateString(): String {
        val date = this
        val format = SimpleDateFormat("EEE, MMM d")
        return format.format(date)
}