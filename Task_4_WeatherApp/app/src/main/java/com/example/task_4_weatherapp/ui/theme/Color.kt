package com.example.task_4_weatherapp.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.task_4_weatherapp.domain.model.WeatherCondition

// ── Light pastel palette ──

// Screen background
val ScreenBg = Color(0xFFF2F0ED)

// Text
val TextPrimary = Color(0xFF2C3A4B)
val TextSecondary = Color(0xFF8E99A4)
val TextTertiary = Color(0xFFB0B8C1)

// Glassmorphic card surface
val GlassWhite = Color(0xCCFFFFFF)       // ~80% white
val GlassBorder = Color(0x33FFFFFF)      // subtle border
val GlassShadow = Color(0x0D000000)      // very soft shadow

// Card backgrounds — soft, desaturated pastels
val SunnyCardTop = Color(0xFFF8F3E8)
val SunnyCardBottom = Color(0xFFEDF2F8)

val CloudyCardTop = Color(0xFFECEFF4)
val CloudyCardBottom = Color(0xFFF5F3F0)

val RainCardTop = Color(0xFFE6EBF4)
val RainCardBottom = Color(0xFFF0EEE9)

val ThunderCardTop = Color(0xFFE8EDF5)
val ThunderCardBottom = Color(0xFFF2F0EB)

val SnowCardTop = Color(0xFFF0F3F8)
val SnowCardBottom = Color(0xFFF8F6F2)

val MistCardTop = Color(0xFFEDEFF2)
val MistCardBottom = Color(0xFFF5F3F0)

val NightCardTop = Color(0xFFE8EAF2)
val NightCardBottom = Color(0xFFF0EEF5)

// Chip colors
val ChipOrangeBg = Color(0xFFFFE0C2)
val ChipOrangeText = Color(0xFFD4761A)
val ChipGreenBg = Color(0xFFD0F0D0)
val ChipGreenText = Color(0xFF2D9A4E)
val ChipBlueBg = Color(0xFFD0E4F5)
val ChipBlueText = Color(0xFF2E7DB8)

// Chart gradient (kept for hourly)
val ChartCool = Color(0xFF5EB7F0)
val ChartWarm = Color(0xFFF5A623)

// Accent
val AccentBlue = Color(0xFF5B9FE8)

// Card background gradient per condition
fun cardGradientFor(condition: WeatherCondition): Brush = when (condition) {
    WeatherCondition.SUNNY -> Brush.linearGradient(
        colors = listOf(SunnyCardTop, SunnyCardBottom),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )
    WeatherCondition.PARTLY_CLOUDY -> Brush.linearGradient(
        colors = listOf(CloudyCardTop, SunnyCardBottom),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )
    WeatherCondition.CLOUDY -> Brush.linearGradient(
        colors = listOf(CloudyCardTop, CloudyCardBottom),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )
    WeatherCondition.RAIN -> Brush.linearGradient(
        colors = listOf(RainCardTop, RainCardBottom),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )
    WeatherCondition.THUNDERSTORM -> Brush.linearGradient(
        colors = listOf(ThunderCardTop, ThunderCardBottom),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )
    WeatherCondition.SNOW -> Brush.linearGradient(
        colors = listOf(SnowCardTop, SnowCardBottom),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )
    WeatherCondition.MIST -> Brush.linearGradient(
        colors = listOf(MistCardTop, MistCardBottom),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )
    WeatherCondition.NIGHT_CLEAR -> Brush.linearGradient(
        colors = listOf(NightCardTop, NightCardBottom),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )
    WeatherCondition.NIGHT_CLOUDY -> Brush.linearGradient(
        colors = listOf(NightCardTop, NightCardBottom),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )
}

// Keep old functions for backward compat — now always dark text on light cards
fun textColorFor(condition: WeatherCondition): Color = TextPrimary
fun subTextColorFor(condition: WeatherCondition): Color = TextSecondary

// ── Deprecated aliases kept so other files compile ──
val ErrorGradient = Brush.verticalGradient(listOf(Color(0xFFF5F0F0), Color(0xFFEDE8E8)))
val GraySubText = TextSecondary
val SkyBlue = AccentBlue
val DarkCharcoal = Color(0xFF3A3A4A)
val DarkNavy = TextPrimary

fun gradientFor(condition: WeatherCondition): Brush = cardGradientFor(condition)
fun highlightOverlayFor(condition: WeatherCondition): Color = Color(0x14000000)