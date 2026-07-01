package com.example.task_4_weatherapp.data.repository

import com.example.task_4_weatherapp.data.remote.WeatherApiService
import com.example.task_4_weatherapp.domain.mapper.WeatherMapper
import com.example.task_4_weatherapp.domain.model.CityWeather

class WeatherRepository(
    private val api: WeatherApiService,
    private val apiKey: String
) {
    suspend fun getCityWeather(cityName: String): CityWeather {
        val current = api.getCurrentWeather(city = cityName, apiKey = apiKey)
        val forecast = api.getForecast(city = cityName, apiKey = apiKey)
        return WeatherMapper.map(current, forecast)
    }
}
