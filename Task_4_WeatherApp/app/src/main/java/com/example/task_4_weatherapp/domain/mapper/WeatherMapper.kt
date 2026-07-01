package com.example.task_4_weatherapp.domain.mapper

import com.example.task_4_weatherapp.data.remote.dto.CurrentWeatherDto
import com.example.task_4_weatherapp.data.remote.dto.ForecastDto
import com.example.task_4_weatherapp.data.remote.dto.ForecastItemDto
import com.example.task_4_weatherapp.domain.model.CityWeather
import com.example.task_4_weatherapp.domain.model.DailyPoint
import com.example.task_4_weatherapp.domain.model.HourlyPoint
import com.example.task_4_weatherapp.domain.model.WeatherCondition
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object WeatherMapper {

    fun map(current: CurrentWeatherDto, forecast: ForecastDto): CityWeather {
        val timezoneOffsetMs = current.timezone * 1000L
        val localTimeMs = (current.dt * 1000L) + timezoneOffsetMs
        val localTimeStr = formatTime(localTimeMs)

        val currentCondition = if (current.weather.isNotEmpty()) {
            WeatherCondition.fromApiData(
                current.weather[0].main,
                current.weather[0].icon
            )
        } else {
            WeatherCondition.CLOUDY
        }

        val description = if (current.weather.isNotEmpty()) {
            current.weather[0].description.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
        } else {
            ""
        }

        val windSpeedKmh = ((current.wind?.speed ?: 0.0) * 3.6).toInt()

        val hourlyPoints = mapHourlyForecast(
            forecastItems = forecast.list.take(5),
            timezoneOffset = current.timezone,
            currentDt = current.dt
        )

        val dailyPoints = mapDailyForecast(
            forecastItems = forecast.list,
            timezoneOffset = current.timezone,
            currentDt = current.dt
        )

        return CityWeather(
            cityName = current.name,
            localTime = localTimeStr,
            condition = currentCondition,
            description = description,
            currentTemp = current.main.temp.toInt(),
            feelsLikeTemp = (current.main.feelsLike ?: current.main.temp).toInt(),
            humidity = current.main.humidity ?: 0,
            windSpeedKmh = windSpeedKmh,
            hourlyForecast = hourlyPoints,
            dailyForecast = dailyPoints
        )
    }

    private fun mapDailyForecast(
        forecastItems: List<ForecastItemDto>,
        timezoneOffset: Int,
        currentDt: Long
    ): List<DailyPoint> {
        val currentLocalMs = (currentDt + timezoneOffset) * 1000L
        val currentCal = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
            timeInMillis = currentLocalMs
        }
        val todayDayOfYear = currentCal.get(Calendar.DAY_OF_YEAR)
        val todayYear = currentCal.get(Calendar.YEAR)

        data class DayData(
            val dayOfYear: Int,
            val year: Int,
            val dayOfWeek: Int,
            val temps: MutableList<Double> = mutableListOf(),
            var condition: WeatherCondition = WeatherCondition.CLOUDY
        )

        val dayMap = linkedMapOf<String, DayData>()

        for (item in forecastItems) {
            val localMs = (item.dt + timezoneOffset) * 1000L
            val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                timeInMillis = localMs
            }
            val dayOfYear = cal.get(Calendar.DAY_OF_YEAR)
            val year = cal.get(Calendar.YEAR)

            if (dayOfYear == todayDayOfYear && year == todayYear) continue

            val key = "$year-$dayOfYear"
            val dayData = dayMap.getOrPut(key) {
                DayData(dayOfYear, year, cal.get(Calendar.DAY_OF_WEEK))
            }
            dayData.temps.add(item.main.temp)

            val hour = cal.get(Calendar.HOUR_OF_DAY)
            if (hour in 11..14 && item.weather.isNotEmpty()) {
                dayData.condition = WeatherCondition.fromApiData(
                    item.weather[0].main, item.weather[0].icon
                )
            } else if (dayData.condition == WeatherCondition.CLOUDY && item.weather.isNotEmpty()) {
                dayData.condition = WeatherCondition.fromApiData(
                    item.weather[0].main, item.weather[0].icon
                )
            }
        }

        val result = mutableListOf<DailyPoint>()
        var isFirst = true

        for ((_, data) in dayMap) {
            if (result.size >= 4) break
            val maxTemp = data.temps.maxOrNull()?.toInt() ?: 0
            val label = if (isFirst) {
                "Ngày mai"
            } else {
                vietnameseDayName(data.dayOfWeek)
            }
            result.add(DailyPoint(label, maxTemp, data.condition))
            isFirst = false
        }

        return result
    }

    private fun vietnameseDayName(dayOfWeek: Int): String = when (dayOfWeek) {
        Calendar.MONDAY -> "Thứ Hai"
        Calendar.TUESDAY -> "Thứ Ba"
        Calendar.WEDNESDAY -> "Thứ Tư"
        Calendar.THURSDAY -> "Thứ Năm"
        Calendar.FRIDAY -> "Thứ Sáu"
        Calendar.SATURDAY -> "Thứ Bảy"
        Calendar.SUNDAY -> "Chủ Nhật"
        else -> ""
    }

    private fun mapHourlyForecast(
        forecastItems: List<ForecastItemDto>,
        timezoneOffset: Int,
        currentDt: Long
    ): List<HourlyPoint> {
        val closestDt = forecastItems.minByOrNull {
            kotlin.math.abs(it.dt - currentDt)
        }?.dt ?: currentDt

        return forecastItems.map { item ->
            val localTimeMs = (item.dt + timezoneOffset) * 1000L
            val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                timeInMillis = localTimeMs
            }
            val hour = cal.get(Calendar.HOUR_OF_DAY)
            val amPm = if (hour < 12) "SA" else "CH"
            val displayHour = when {
                hour == 0 -> 12
                hour > 12 -> hour - 12
                else -> hour
            }
            val label = "${displayHour}${amPm}"

            val condition = if (item.weather.isNotEmpty()) {
                WeatherCondition.fromApiData(item.weather[0].main, item.weather[0].icon)
            } else {
                WeatherCondition.CLOUDY
            }

            HourlyPoint(
                hourLabel = label,
                temp = item.main.temp.toInt(),
                condition = condition,
                isNow = item.dt == closestDt
            )
        }
    }

    private fun formatTime(utcMillis: Long): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(Date(utcMillis))
    }
}
