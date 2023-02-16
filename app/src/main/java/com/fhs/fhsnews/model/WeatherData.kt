package com.fhs.fhsnews.model

import java.util.*

data class WeatherData(
    var time: Date,
    var temp: Int,
    var weather_icon_id: String,
    var weather_description: String
)