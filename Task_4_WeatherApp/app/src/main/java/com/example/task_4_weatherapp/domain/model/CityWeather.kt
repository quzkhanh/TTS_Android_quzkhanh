package com.example.task_4_weatherapp.domain.model

data class CityWeather(
    val cityName: String,
    val localTime: String,
    val condition: WeatherCondition,
    val description: String,
    val currentTemp: Int,
    val feelsLikeTemp: Int,
    val humidity: Int,
    val windSpeedKmh: Int,
    val hourlyForecast: List<HourlyPoint>,
    val dailyForecast: List<DailyPoint>
)

data class HourlyPoint(
    val hourLabel: String,
    val temp: Int,
    val condition: WeatherCondition,
    val isNow: Boolean
)

data class DailyPoint(
    val dayLabel: String,
    val temp: Int,
    val condition: WeatherCondition
)
