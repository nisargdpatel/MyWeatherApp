package com.example.weatherapp.datalibrary.repository

import com.example.weatherapp.datalibrary.api.WeatherDataServiceCalls
import com.example.weatherapp.datalibrary.mapper.convertToLocalTime
import com.example.weatherapp.datalibrary.mapper.toWeatherDetailsUi
import com.example.weatherapp.datalibrary.mapper.toWeatherUi
import com.example.weatherapp.datalibrary.utils.CurrentWeatherDataResponse
import com.example.weatherapp.datalibrary.utils.HourlyWeatherDetails
import com.example.weatherapp.domain.repository.WeatherDataRepository
import javax.inject.Inject

const val appId = "c9d841f9558a6304ca2daf15fcc3ca57"

class WeatherDataRepositoryImpl @Inject constructor(
    private val api: WeatherDataServiceCalls
): WeatherDataRepository {
    override suspend fun getCurrentWeatherData(
        lat: Double,
        long: Double
    ): Result<CurrentWeatherDataResponse> {
        return try {
            val response = api.getCurrentWeatherData(
                lat.toString(), long.toString(), appId
            )
            val listOfHourlyWeather = listOf(
                HourlyWeatherDetails(time = response.listOfHourlyWeather[0].time.convertToLocalTime(),
                    currentWeather = response.listOfHourlyWeather[0].currentWeather.toWeatherDetailsUi(),
                weather = response.listOfHourlyWeather[0].weather.toWeatherUi(),
                probabilityOfPrecipitation = response.listOfHourlyWeather[0].probabilityOfPrecipitation)
            )
            Result.success(value = CurrentWeatherDataResponse(listOfHourlyWeather))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getCurrentWeatherWithCity(city: String): Result<CurrentWeatherDataResponse> {
        return try {
            val response = api.getCurrentWeatherWithCity(city, appId)
            val listOfHourlyWeather = listOf(
                HourlyWeatherDetails(time = response.listOfHourlyWeather[0].time.convertToLocalTime(),
                    currentWeather = response.listOfHourlyWeather[0].currentWeather.toWeatherDetailsUi(),
                    weather = response.listOfHourlyWeather[0].weather.toWeatherUi(),
                    probabilityOfPrecipitation = response.listOfHourlyWeather[0].probabilityOfPrecipitation)
            )
            Result.success(value = CurrentWeatherDataResponse(listOfHourlyWeather))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}