package com.fhs.fhsnews.model

import java.util.*

data class WeatherData(
    val itemType: String = "WeatherData",
    val time: Date,
    val temp: Int,
    val weather_icon_id: String,
    val weather_description: String
)