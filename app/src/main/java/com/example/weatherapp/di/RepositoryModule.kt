package com.example.weatherapp.di

import com.example.weatherapp.datalibrary.repository.WeatherDataRepositoryImpl
import com.example.weatherapp.domain.repository.WeatherDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherDataRepository(
        weatherDataRepositoryImpl: WeatherDataRepositoryImpl
    ): WeatherDataRepository
}