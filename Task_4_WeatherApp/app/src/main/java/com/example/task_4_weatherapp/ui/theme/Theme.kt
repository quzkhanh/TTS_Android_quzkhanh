package com.example.task_4_weatherapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val WeatherLightScheme = lightColorScheme(
    primary = AccentBlue,
    onPrimary = Color.White,
    secondary = Color(0xFF7EB8DA),
    onSecondary = Color.White,
    background = ScreenBg,
    onBackground = TextPrimary,
    surface = Color.White,
    onSurface = TextPrimary,
    surfaceVariant = Color(0xFFF0ECE8),
    onSurfaceVariant = TextSecondary
)

@Composable
fun Task_4_WeatherAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = WeatherLightScheme,
        typography = Typography,
        content = content
    )
}