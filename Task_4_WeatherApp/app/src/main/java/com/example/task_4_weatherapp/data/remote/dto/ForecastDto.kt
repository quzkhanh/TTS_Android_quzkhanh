package com.example.task_4_weatherapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastDto(
    @Json(name = "list") val list: List<ForecastItemDto>,
    @Json(name = "city") val city: CityDto
)

@JsonClass(generateAdapter = true)
data class ForecastItemDto(
    @Json(name = "dt") val dt: Long,
    @Json(name = "main") val main: MainDto,
    @Json(name = "weather") val weather: List<WeatherInfoDto>,
    @Json(name = "dt_txt") val dtTxt: String
)

@JsonClass(generateAdapter = true)
data class CityDto(
    @Json(name = "name") val name: String,
    @Json(name = "timezone") val timezone: Int
)
