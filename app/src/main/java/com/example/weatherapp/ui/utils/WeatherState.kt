package com.example.weatherapp.ui.utils

import com.example.weatherapp.datalibrary.utils.CurrentWeatherDataResponse
import com.example.weatherapp.datalibrary.utils.HourlyWeatherDataResponse

data class WeatherState(
    val currentWeatherDataResponse: CurrentWeatherDataResponse? = null,
    val hourlyWeatherDataResponse: HourlyWeatherDataResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)