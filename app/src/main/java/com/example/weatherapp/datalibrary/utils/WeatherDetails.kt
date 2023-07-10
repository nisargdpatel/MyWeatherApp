package com.example.weatherapp.datalibrary.utils

import com.squareup.moshi.Json

data class WeatherDetails(
    @field:Json(name = "temp")
    val temperature: Double,
    @field:Json(name = "feels_like")
    val feelsLike: Double,
    @field:Json(name = "temp_min")
    val minimumTemperature: Double,
    @field:Json(name = "temp_max")
    val maximumTemperature: Double,
    @field:Json(name = "pressure")
    val pressure: Double,
    @field:Json(name = "humidity")
    val humidity: Double,
    @field:Json(name = "sea_level")
    val seaLevel: Double,
    @field:Json(name = "grnd_level")
    val groundLevel: Double,
)
