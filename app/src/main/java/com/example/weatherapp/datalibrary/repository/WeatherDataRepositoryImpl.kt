package com.example.weatherapp.datalibrary.repository

import com.example.weatherapp.datalibrary.api.WeatherDataServiceCalls
import com.example.weatherapp.datalibrary.mapper.convertToLocalTime
import com.example.weatherapp.datalibrary.mapper.trimList
import com.example.weatherapp.datalibrary.mapper.toWeatherUi
import com.example.weatherapp.datalibrary.utils.CurrentWeatherDataResponse
import com.example.weatherapp.datalibrary.utils.HourlyWeatherDetails
import com.example.weatherapp.domain.repository.WeatherDataRepository
import javax.inject.Inject

private const val appId = "c9d841f9558a6304ca2daf15fcc3ca57"

class WeatherDataRepositoryImpl @Inject constructor(
    private val api: WeatherDataServiceCalls
): WeatherDataRepository {
    override suspend fun getCurrentWeatherData(
        lat: Double,
        long: Double
    ): Result<CurrentWeatherDataResponse> {
        return try {
            val response = api.getCurrentWeatherData(
                lat.toString(), long.toString(), appid = appId
            )
            val trimmedList = response.listOfHourlyWeather.toMutableList().trimList()
            val listOfHourlyWeather = mutableListOf<HourlyWeatherDetails>()

            trimmedList.forEach {
                    listOfHourlyWeather.add(HourlyWeatherDetails(time = it.time.convertToLocalTime(),
                        currentWeather = it.currentWeather,
                        hourlyWeather = it.hourlyWeather.toWeatherUi(),
                        probabilityOfPrecipitation = it.probabilityOfPrecipitation))
            }
            Result.success(value = CurrentWeatherDataResponse(listOfHourlyWeather))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getCurrentWeatherWithCity(city: String): Result<CurrentWeatherDataResponse> {
        return try {
            val response = api.getCurrentWeatherWithCity(city, appid = appId)
            val reorderedList = response.listOfHourlyWeather.toMutableList().trimList()
            val listOfHourlyWeather = mutableListOf<HourlyWeatherDetails>()

            reorderedList.forEach {
                listOfHourlyWeather.add(HourlyWeatherDetails(time = it.time.convertToLocalTime(),
                    currentWeather = it.currentWeather,
                    hourlyWeather = it.hourlyWeather.toWeatherUi(),
                    probabilityOfPrecipitation = it.probabilityOfPrecipitation))
            }

            Result.success(value = CurrentWeatherDataResponse(listOfHourlyWeather))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}