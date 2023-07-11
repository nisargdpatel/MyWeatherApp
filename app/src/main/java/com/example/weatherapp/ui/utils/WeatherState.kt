package com.example.weatherapp.ui.utils

import com.example.weatherapp.datalibrary.utils.CurrentWeatherDataResponse

data class WeatherState(
    val currentWeatherDataResponse: CurrentWeatherDataResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)