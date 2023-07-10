package com.example.weatherapp.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.LocationTracker
import com.example.weatherapp.domain.repository.WeatherDataRepository
import com.example.weatherapp.ui.utils.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherDataRepository,
    private val locationTracker: LocationTracker
): ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun searchWeather() {
        loadWeatherInfo(_searchText.value)
    }

    fun loadWeatherInfo(city: String? = null) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )

            locationTracker.getCurrentLocation()?.let { location ->

                Log.d("Nisarg", city?:"No city")
                val currentWeatherResult = if (city.isNullOrEmpty()) {
                    repository.getCurrentWeatherData(location.latitude, location.longitude)
                } else {
                    repository.getCurrentWeatherWithCity(city)
                }
            state = if (currentWeatherResult.isSuccess) {
                state.copy(
                    currentWeatherDataResponse = currentWeatherResult.getOrNull(),
//                    hourlyWeatherDataResponse = hourlyWeatherResult.getOrNull(),
                    isLoading = false,
                    error = null
                )
            } else {
                state.copy(
//                    currentWeatherDataResponse = null,
                    hourlyWeatherDataResponse = null,
                    isLoading = false,
                    error = currentWeatherResult.exceptionOrNull()?.message
                )
            }

            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }
}