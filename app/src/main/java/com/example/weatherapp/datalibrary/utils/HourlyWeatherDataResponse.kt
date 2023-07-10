package com.example.weatherapp.datalibrary.utils

import com.squareup.moshi.Json

data class HourlyWeatherDataResponse(
    @field:Json(name = "list")
    val listOfHourlyWeather: List<HourlyWeatherDetails>
)
