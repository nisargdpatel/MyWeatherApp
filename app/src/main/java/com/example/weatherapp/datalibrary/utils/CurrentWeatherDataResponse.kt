package com.example.weatherapp.datalibrary.utils

import com.squareup.moshi.Json

data class CurrentWeatherDataResponse(
    @field:Json(name = "list")
    val listOfHourlyWeather: List<HourlyWeatherDetails>
)