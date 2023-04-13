package com.fhs.fhsnews.model

import java.util.*

data class WeatherData(
	var time: Date,
	var current_temp: String,
	var weather_icon_id: String,
	var weather_description: String = "Loading..."
)