package com.example.weatherapp.datalibrary.utils

import com.squareup.moshi.Json

data class WeatherInfo(
    @field:Json(name = "main")
    val weatherCondition: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "icon")
    val icon: String
)