package com.example.task_4_weatherapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.task_4_weatherapp.ui.theme.WeatherTypography

@Composable
fun HeaderRow(
    cityName: String,
    time: String,
    textColor: Color,
    subTextColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = cityName,
            style = WeatherTypography.CityName,
            color = textColor
        )
        Text(
            text = time,
            style = WeatherTypography.TimeLabel,
            color = subTextColor
        )
    }
}
