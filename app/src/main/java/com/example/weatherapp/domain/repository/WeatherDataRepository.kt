package com.example.weatherapp.domain.repository

import com.example.weatherapp.datalibrary.utils.CurrentWeatherDataResponse

interface WeatherDataRepository {
    suspend fun getCurrentWeatherData(lat: Double, long: Double): Result<CurrentWeatherDataResponse>
    suspend fun getCurrentWeatherWithCity(city: String): Result<CurrentWeatherDataResponse>
}