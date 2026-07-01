package com.example.task_4_weatherapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeatherDto(
    @Json(name = "name") val name: String,
    @Json(name = "main") val main: MainDto,
    @Json(name = "weather") val weather: List<WeatherInfoDto>,
    @Json(name = "wind") val wind: WindDto? = null,
    @Json(name = "dt") val dt: Long,
    @Json(name = "timezone") val timezone: Int,
    @Json(name = "cod") val cod: Int
)

@JsonClass(generateAdapter = true)
data class MainDto(
    @Json(name = "temp") val temp: Double,
    @Json(name = "feels_like") val feelsLike: Double? = null,
    @Json(name = "temp_min") val tempMin: Double? = null,
    @Json(name = "temp_max") val tempMax: Double? = null,
    @Json(name = "humidity") val humidity: Int? = null
)

@JsonClass(generateAdapter = true)
data class WeatherInfoDto(
    @Json(name = "id") val id: Int,
    @Json(name = "main") val main: String,
    @Json(name = "description") val description: String,
    @Json(name = "icon") val icon: String
)

@JsonClass(generateAdapter = true)
data class WindDto(
    @Json(name = "speed") val speed: Double,
    @Json(name = "deg") val deg: Int? = null
)
