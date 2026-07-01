package com.example.task_4_weatherapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.task_4_weatherapp.domain.model.CityWeather
import com.example.task_4_weatherapp.domain.model.WeatherCondition
import com.example.task_4_weatherapp.ui.theme.AccentBlue
import com.example.task_4_weatherapp.ui.theme.ChipBlueBg
import com.example.task_4_weatherapp.ui.theme.ChipBlueText
import com.example.task_4_weatherapp.ui.theme.ChipGreenBg
import com.example.task_4_weatherapp.ui.theme.ChipGreenText
import com.example.task_4_weatherapp.ui.theme.ChipOrangeBg
import com.example.task_4_weatherapp.ui.theme.ChipOrangeText
import com.example.task_4_weatherapp.ui.theme.GlassWhite
import com.example.task_4_weatherapp.ui.theme.TextPrimary
import com.example.task_4_weatherapp.ui.theme.TextSecondary
import com.example.task_4_weatherapp.ui.theme.TextTertiary
import com.example.task_4_weatherapp.ui.theme.WeatherTypography
import com.example.task_4_weatherapp.ui.theme.cardGradientFor

@Composable
fun WeatherCard(
    data: CityWeather,
    modifier: Modifier = Modifier
) {
    val bgBrush = cardGradientFor(data.condition)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(32.dp))
            .background(brush = bgBrush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ── Top bar ──
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = TextTertiary,
                    modifier = Modifier.size(22.dp)
                )
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = TextTertiary,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ── City name + description ──
            Text(
                text = data.cityName,
                style = WeatherTypography.CityNameLarge,
                color = TextPrimary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = data.description,
                style = WeatherTypography.Description,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ── Weather icon area with glassmorphic overlay cards ──
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                WeatherIcon(
                    condition = data.condition,
                    size = 120.dp
                )

                // Small glass card: feels-like temp (bottom-start)
                GlassInfoCard(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(x = 12.dp, y = (-4).dp)
                ) {
                    Text(
                        text = "${data.feelsLikeTemp}°",
                        style = WeatherTypography.TempSmall,
                        color = TextPrimary
                    )
                }

                // Small glass card: rain/humidity icon (center-end)
                GlassInfoCard(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = (-12).dp)
                ) {
                    // Tiny raindrop drawn with Canvas
                    Canvas(modifier = Modifier.size(18.dp)) {
                        val cx = center.x
                        val cy = center.y
                        val r = size.minDimension * 0.2f
                        drawCircle(AccentBlue, r, Offset(cx, cy + r * 0.6f))
                        val path = Path().apply {
                            moveTo(cx, cy - r * 1.8f)
                            lineTo(cx - r * 1.1f, cy)
                            lineTo(cx + r * 1.1f, cy)
                            close()
                        }
                        drawPath(path, AccentBlue)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ── Humidity + Temperature + Wind ──
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Humidity with tiny drop icon
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Canvas(modifier = Modifier.size(16.dp)) {
                        val cx = center.x
                        val cy = center.y
                        val r = size.minDimension * 0.18f
                        drawCircle(AccentBlue.copy(alpha = 0.7f), r, Offset(cx, cy + r * 0.5f))
                        val p = Path().apply {
                            moveTo(cx, cy - r * 1.5f)
                            lineTo(cx - r * 0.9f, cy)
                            lineTo(cx + r * 0.9f, cy)
                            close()
                        }
                        drawPath(p, AccentBlue.copy(alpha = 0.7f))
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${data.humidity}%",
                        style = WeatherTypography.InfoLabel,
                        color = TextSecondary
                    )
                }

                // Temperature (large)
                Text(
                    text = "${data.currentTemp}°",
                    style = WeatherTypography.TempDisplay,
                    color = TextPrimary
                )

                // Wind with tiny wind icon
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Canvas(modifier = Modifier.size(16.dp)) {
                        val s = size.minDimension
                        val y1 = center.y - s * 0.15f
                        val y2 = center.y + s * 0.05f
                        val y3 = center.y + s * 0.25f
                        val startX = center.x - s * 0.3f
                        drawLine(TextSecondary, Offset(startX, y1), Offset(startX + s * 0.55f, y1), s * 0.08f, cap = StrokeCap.Round)
                        drawLine(TextSecondary, Offset(startX, y2), Offset(startX + s * 0.65f, y2), s * 0.08f, cap = StrokeCap.Round)
                        drawLine(TextSecondary, Offset(startX, y3), Offset(startX + s * 0.45f, y3), s * 0.08f, cap = StrokeCap.Round)
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${data.windSpeedKmh}km/h",
                        style = WeatherTypography.InfoLabel,
                        color = TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // ── Info chips (UV, Pollution, Pollen) ──
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val uv = estimateUvLevel(data.condition)
                val pollution = estimatePollutionLevel(data.condition)

                InfoChip(uv.first, uv.second, uv.third, "UV")
                InfoChip(pollution.first, pollution.second, pollution.third, "Ô nhiễm")
                InfoChip("Vừa", ChipBlueBg, ChipBlueText, "Phấn hoa")
            }

            Spacer(modifier = Modifier.height(14.dp))

            // ── Daily forecast ──
            DailyForecastRow(
                points = data.dailyForecast,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun GlassInfoCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(GlassWhite)
            .border(1.dp, Color.White.copy(alpha = 0.6f), RoundedCornerShape(14.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun InfoChip(
    level: String,
    bgColor: Color,
    textColor: Color,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(bgColor)
                .padding(horizontal = 14.dp, vertical = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = level, style = WeatherTypography.ChipText, color = textColor)
        }
        Text(text = label, style = WeatherTypography.ChipLabel, color = TextSecondary)
    }
}

private fun estimateUvLevel(c: WeatherCondition): Triple<String, Color, Color> = when (c) {
    WeatherCondition.SUNNY -> Triple("Cao", ChipOrangeBg, ChipOrangeText)
    WeatherCondition.PARTLY_CLOUDY -> Triple("Vừa", ChipBlueBg, ChipBlueText)
    else -> Triple("Thấp", ChipGreenBg, ChipGreenText)
}

private fun estimatePollutionLevel(c: WeatherCondition): Triple<String, Color, Color> = when (c) {
    WeatherCondition.MIST -> Triple("Cao", ChipOrangeBg, ChipOrangeText)
    else -> Triple("Thấp", ChipGreenBg, ChipGreenText)
}
