package com.example.weatherapp.datalibrary.api

import com.example.weatherapp.datalibrary.utils.CurrentWeatherDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherDataServiceCalls {
    @GET("data/2.5/forecast?")
    suspend fun getCurrentWeatherData(
        @Query("lat") lat: String,
        @Query("lon") long: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String
    ): CurrentWeatherDataResponse

    @GET("data/2.5/forecast?")
    suspend fun getCurrentWeatherWithCity(
        @Query("q") cityName: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String
    ): CurrentWeatherDataResponse
}