package com.example.task_4_weatherapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.task_4_weatherapp.domain.model.WeatherCondition
import kotlin.math.cos
import kotlin.math.sin

// ── Icon color palette ──
private val SunYellow = Color(0xFFFFCA28)
private val SunOrange = Color(0xFFFFB300)
private val SunGlow = Color(0x30FFEB3B)
private val CloudWhite = Color(0xFFF5F5F5)
private val CloudLight = Color(0xFFECEFF1)
private val CloudGray = Color(0xFFCFD8DC)
private val CloudDark = Color(0xFF90A4AE)
private val RainBlue = Color(0xFF64B5F6)
private val LightningYellow = Color(0xFFFFD54F)
private val MoonGold = Color(0xFFFFE082)
private val MoonBright = Color(0xFFFFF176)
private val StarColor = Color(0xFFFFF9C4)
private val SnowDot = Color(0xFFE3F2FD)

@Composable
fun WeatherIcon(
    condition: WeatherCondition,
    size: Dp = 100.dp,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(size)) {
        when (condition) {
            WeatherCondition.SUNNY -> drawSun()
            WeatherCondition.PARTLY_CLOUDY -> drawPartlyCloudy()
            WeatherCondition.CLOUDY -> drawClouds()
            WeatherCondition.RAIN -> drawRain()
            WeatherCondition.THUNDERSTORM -> drawThunderstorm()
            WeatherCondition.SNOW -> drawSnow()
            WeatherCondition.MIST -> drawMist()
            WeatherCondition.NIGHT_CLEAR -> drawMoon()
            WeatherCondition.NIGHT_CLOUDY -> drawNightCloudy()
        }
    }
}

@Composable
fun WeatherIconSmall(
    condition: WeatherCondition,
    modifier: Modifier = Modifier
) {
    WeatherIcon(condition = condition, size = 28.dp, modifier = modifier)
}

// ────────────────────────────────────────────
// Drawing functions
// ────────────────────────────────────────────

private fun DrawScope.drawSun() {
    val cx = center.x
    val cy = center.y
    val r = size.minDimension * 0.18f

    // Outer glow
    drawCircle(SunGlow, r * 2f, center)

    // Rays
    for (i in 0 until 8) {
        val angle = i * 45.0 * (Math.PI / 180.0)
        val innerR = r * 1.25f
        val outerR = r * 1.7f
        drawLine(
            color = SunOrange.copy(alpha = 0.7f),
            start = Offset(
                cx + (cos(angle) * innerR).toFloat(),
                cy + (sin(angle) * innerR).toFloat()
            ),
            end = Offset(
                cx + (cos(angle) * outerR).toFloat(),
                cy + (sin(angle) * outerR).toFloat()
            ),
            strokeWidth = r * 0.2f,
            cap = StrokeCap.Round
        )
    }

    // Sun body with radial gradient
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(SunYellow, SunOrange),
            center = Offset(cx - r * 0.15f, cy - r * 0.15f),
            radius = r * 1.3f
        ),
        radius = r,
        center = center
    )
}

private fun DrawScope.drawCloud(
    cx: Float,
    cy: Float,
    scale: Float,
    bodyColor: Color = CloudWhite,
    shadowColor: Color = CloudLight
) {
    // Soft shadow layer (slightly below and behind)
    drawCircle(shadowColor, scale * 0.27f, Offset(cx - scale * 0.14f, cy + scale * 0.06f))
    drawCircle(shadowColor, scale * 0.21f, Offset(cx + scale * 0.18f, cy + scale * 0.06f))
    drawCircle(shadowColor, scale * 0.16f, Offset(cx - scale * 0.34f, cy + scale * 0.05f))

    // Bottom fill
    drawRoundRect(
        color = bodyColor,
        topLeft = Offset(cx - scale * 0.44f, cy - scale * 0.02f),
        size = Size(scale * 0.82f, scale * 0.18f),
        cornerRadius = CornerRadius(scale * 0.06f)
    )

    // Cloud bumps (overlapping circles)
    drawCircle(bodyColor, scale * 0.24f, Offset(cx - scale * 0.14f, cy - scale * 0.02f))
    drawCircle(bodyColor, scale * 0.30f, Offset(cx + scale * 0.06f, cy - scale * 0.10f))
    drawCircle(bodyColor, scale * 0.19f, Offset(cx + scale * 0.27f, cy))
    drawCircle(bodyColor, scale * 0.17f, Offset(cx - scale * 0.32f, cy + scale * 0.02f))
}

private fun DrawScope.drawPartlyCloudy() {
    val s = size.minDimension

    // Sun behind cloud (upper right)
    val sunCx = center.x + s * 0.14f
    val sunCy = center.y - s * 0.14f
    val sunR = s * 0.11f

    drawCircle(SunGlow, sunR * 1.8f, Offset(sunCx, sunCy))
    for (i in 0 until 8) {
        val angle = i * 45.0 * (Math.PI / 180.0)
        drawLine(
            color = SunOrange.copy(alpha = 0.5f),
            start = Offset(
                sunCx + (cos(angle) * sunR * 1.2f).toFloat(),
                sunCy + (sin(angle) * sunR * 1.2f).toFloat()
            ),
            end = Offset(
                sunCx + (cos(angle) * sunR * 1.6f).toFloat(),
                sunCy + (sin(angle) * sunR * 1.6f).toFloat()
            ),
            strokeWidth = sunR * 0.2f,
            cap = StrokeCap.Round
        )
    }
    drawCircle(
        brush = Brush.radialGradient(
            listOf(SunYellow, SunOrange), Offset(sunCx, sunCy), sunR
        ),
        radius = sunR,
        center = Offset(sunCx, sunCy)
    )

    // Cloud in front
    drawCloud(center.x - s * 0.04f, center.y + s * 0.06f, s * 0.48f)
}

private fun DrawScope.drawClouds() {
    val s = size.minDimension
    // Back cloud (darker)
    drawCloud(center.x + s * 0.06f, center.y - s * 0.06f, s * 0.40f, CloudGray, Color(0xFFB0BEC5))
    // Front cloud
    drawCloud(center.x - s * 0.04f, center.y + s * 0.06f, s * 0.48f)
}

private fun DrawScope.drawRain() {
    val s = size.minDimension
    // Gray cloud
    drawCloud(center.x, center.y - s * 0.1f, s * 0.46f, CloudGray, Color(0xFFB0BEC5))

    // Raindrops
    val drops = listOf(
        Offset(center.x - s * 0.12f, center.y + s * 0.16f),
        Offset(center.x + s * 0.03f, center.y + s * 0.21f),
        Offset(center.x + s * 0.16f, center.y + s * 0.14f)
    )
    for (pos in drops) {
        drawDrop(pos, s * 0.032f, RainBlue)
    }
}

private fun DrawScope.drawDrop(tip: Offset, r: Float, color: Color) {
    // Teardrop: rounded bottom + pointed top
    drawCircle(color, r, Offset(tip.x, tip.y + r * 0.8f))
    val path = Path().apply {
        moveTo(tip.x, tip.y - r * 1.2f)
        lineTo(tip.x - r * 0.9f, tip.y + r * 0.2f)
        lineTo(tip.x + r * 0.9f, tip.y + r * 0.2f)
        close()
    }
    drawPath(path, color)
}

private fun DrawScope.drawThunderstorm() {
    val s = size.minDimension
    // Dark cloud
    drawCloud(center.x, center.y - s * 0.12f, s * 0.50f, CloudDark, Color(0xFF78909C))

    // Lightning bolt
    val bx = center.x
    val by = center.y + s * 0.06f
    val bolt = Path().apply {
        moveTo(bx + s * 0.02f, by)
        lineTo(bx - s * 0.05f, by + s * 0.11f)
        lineTo(bx - s * 0.005f, by + s * 0.11f)
        lineTo(bx - s * 0.04f, by + s * 0.24f)
        lineTo(bx + s * 0.06f, by + s * 0.09f)
        lineTo(bx + s * 0.015f, by + s * 0.09f)
        lineTo(bx + s * 0.055f, by)
        close()
    }
    drawPath(bolt, LightningYellow)

    // Small rain drop
    drawDrop(Offset(center.x - s * 0.14f, center.y + s * 0.14f), s * 0.022f, RainBlue.copy(alpha = 0.6f))
}

private fun DrawScope.drawSnow() {
    val s = size.minDimension
    // Light cloud
    drawCloud(center.x, center.y - s * 0.08f, s * 0.45f)

    // Snowflakes
    val flakes = listOf(
        Offset(center.x - s * 0.13f, center.y + s * 0.15f),
        Offset(center.x + s * 0.01f, center.y + s * 0.20f),
        Offset(center.x + s * 0.14f, center.y + s * 0.13f),
        Offset(center.x - s * 0.05f, center.y + s * 0.27f),
        Offset(center.x + s * 0.09f, center.y + s * 0.25f)
    )
    for (pos in flakes) {
        drawCircle(SnowDot, s * 0.022f, pos)
        // Small cross for snowflake effect
        val arm = s * 0.015f
        drawLine(SnowDot, Offset(pos.x - arm, pos.y), Offset(pos.x + arm, pos.y), strokeWidth = s * 0.006f)
        drawLine(SnowDot, Offset(pos.x, pos.y - arm), Offset(pos.x, pos.y + arm), strokeWidth = s * 0.006f)
    }
}

private fun DrawScope.drawMist() {
    val s = size.minDimension

    val lines = listOf(
        Triple(center.y - s * 0.15f, s * 0.45f, 0.22f),
        Triple(center.y - s * 0.05f, s * 0.55f, 0.35f),
        Triple(center.y + s * 0.05f, s * 0.50f, 0.28f),
        Triple(center.y + s * 0.15f, s * 0.40f, 0.18f)
    )
    val h = s * 0.038f
    for ((y, width, alpha) in lines) {
        drawRoundRect(
            color = CloudGray.copy(alpha = alpha),
            topLeft = Offset(center.x - width / 2, y - h / 2),
            size = Size(width, h),
            cornerRadius = CornerRadius(h / 2)
        )
    }
}

private fun DrawScope.drawMoon() {
    val s = size.minDimension
    val moonR = s * 0.22f

    // Soft glow
    drawCircle(Color(0x18FFE082), moonR * 2f, center)

    // Crescent: draw moon circle, clip out the "shadow" part
    val maskPath = Path().apply {
        addOval(
            Rect(
                center = Offset(center.x + moonR * 0.50f, center.y - moonR * 0.18f),
                radius = moonR * 0.85f
            )
        )
    }
    clipPath(maskPath, ClipOp.Difference) {
        drawCircle(
            brush = Brush.radialGradient(
                listOf(MoonBright, MoonGold),
                center,
                moonR
            ),
            radius = moonR,
            center = center
        )
    }

    // A few stars
    val stars = listOf(
        Offset(center.x + s * 0.24f, center.y - s * 0.20f),
        Offset(center.x - s * 0.22f, center.y - s * 0.12f),
        Offset(center.x + s * 0.18f, center.y + s * 0.22f),
        Offset(center.x - s * 0.16f, center.y + s * 0.18f)
    )
    for (pos in stars) {
        drawCircle(StarColor.copy(alpha = 0.7f), s * 0.012f, pos)
    }
}

private fun DrawScope.drawNightCloudy() {
    val s = size.minDimension

    // Small crescent behind (upper right)
    val moonCx = center.x + s * 0.16f
    val moonCy = center.y - s * 0.16f
    val moonR = s * 0.09f

    drawCircle(Color(0x15FFE082), moonR * 1.8f, Offset(moonCx, moonCy))

    val maskPath = Path().apply {
        addOval(
            Rect(
                center = Offset(moonCx + moonR * 0.50f, moonCy - moonR * 0.18f),
                radius = moonR * 0.85f
            )
        )
    }
    clipPath(maskPath, ClipOp.Difference) {
        drawCircle(MoonGold, moonR, Offset(moonCx, moonCy))
    }

    // Cloud in front
    drawCloud(center.x - s * 0.02f, center.y + s * 0.04f, s * 0.48f, CloudGray, Color(0xFF78909C))
}
