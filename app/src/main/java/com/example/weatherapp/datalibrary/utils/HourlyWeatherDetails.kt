package com.example.weatherapp.datalibrary.utils

import com.squareup.moshi.Json

data class HourlyWeatherDetails(
    @field:Json(name = "dt")
    val time: String,
    @field:Json(name = "main")
    val currentWeather: WeatherDetails,
    @field:Json(name = "weather")
    val hourlyWeather: List<WeatherInfo>,
    @field:Json(name = "pop")
    val probabilityOfPrecipitation: Double
)
