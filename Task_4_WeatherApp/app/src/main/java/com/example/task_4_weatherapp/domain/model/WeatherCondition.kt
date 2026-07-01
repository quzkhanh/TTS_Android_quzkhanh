package com.example.task_4_weatherapp.domain.model

enum class WeatherCondition {
    SUNNY,
    PARTLY_CLOUDY,
    CLOUDY,
    RAIN,
    THUNDERSTORM,
    SNOW,
    MIST,
    NIGHT_CLEAR,
    NIGHT_CLOUDY;

    companion object {
        fun fromApiData(main: String, iconCode: String): WeatherCondition {
            val isNight = iconCode.endsWith("n")
            return when {
                main.equals("Thunderstorm", ignoreCase = true) -> THUNDERSTORM
                main.equals("Drizzle", ignoreCase = true) -> RAIN
                main.equals("Rain", ignoreCase = true) -> RAIN
                main.equals("Snow", ignoreCase = true) -> SNOW
                main.equals("Mist", ignoreCase = true) -> MIST
                main.equals("Haze", ignoreCase = true) -> MIST
                main.equals("Fog", ignoreCase = true) -> MIST
                main.equals("Clear", ignoreCase = true) && isNight -> NIGHT_CLEAR
                main.equals("Clear", ignoreCase = true) -> SUNNY
                main.equals("Clouds", ignoreCase = true) && isNight -> NIGHT_CLOUDY
                main.equals("Clouds", ignoreCase = true) && iconCode == "02d" -> PARTLY_CLOUDY
                main.equals("Clouds", ignoreCase = true) && iconCode == "02n" -> NIGHT_CLOUDY
                main.equals("Clouds", ignoreCase = true) -> CLOUDY
                else -> CLOUDY
            }
        }
    }
}
