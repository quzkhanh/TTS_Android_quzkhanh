package com.example.task_4_weatherapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.task_4_weatherapp.domain.model.DailyPoint
import com.example.task_4_weatherapp.ui.theme.GlassWhite
import com.example.task_4_weatherapp.ui.theme.TextPrimary
import com.example.task_4_weatherapp.ui.theme.TextSecondary
import com.example.task_4_weatherapp.ui.theme.WeatherTypography

@Composable
fun DailyForecastRow(
    points: List<DailyPoint>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 0.dp)
    ) {
        items(points) { point ->
            DailyForecastItem(point)
        }
    }
}

@Composable
private fun DailyForecastItem(point: DailyPoint) {
    Column(
        modifier = Modifier
            .width(72.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(GlassWhite)
            .border(1.dp, Color.White.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
            .padding(vertical = 12.dp, horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WeatherIconSmall(condition = point.condition)
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "${point.temp}°",
            style = WeatherTypography.DayTemp,
            color = TextPrimary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = point.dayLabel,
            style = WeatherTypography.DayLabel,
            color = TextSecondary,
            maxLines = 1
        )
    }
}
