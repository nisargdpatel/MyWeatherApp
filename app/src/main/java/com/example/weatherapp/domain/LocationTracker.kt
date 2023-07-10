package com.example.weatherapp.domain

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}