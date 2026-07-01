package com.example.task_4_weatherapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.task_4_weatherapp.domain.model.HourlyPoint
import com.example.task_4_weatherapp.ui.theme.ChartCool
import com.example.task_4_weatherapp.ui.theme.ChartWarm

@Composable
fun TempLineChart(
    hourlyPoints: List<HourlyPoint>,
    modifier: Modifier = Modifier
) {
    if (hourlyPoints.isEmpty()) return

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val padding = 16.dp.toPx()

        val temps = hourlyPoints.map { it.temp.toFloat() }
        val minTemp = (temps.minOrNull() ?: 0f) - 2f
        val maxTemp = (temps.maxOrNull() ?: 0f) + 2f
        val tempRange = if (maxTemp - minTemp == 0f) 1f else maxTemp - minTemp

        val points = temps.mapIndexed { index, temp ->
            val x = padding + (width - 2 * padding) * index / (temps.size - 1).coerceAtLeast(1)
            val y = height - padding - (height - 2 * padding) * (temp - minTemp) / tempRange
            Offset(x, y)
        }

        // Draw cubic bezier path through points
        val path = Path()
        if (points.isNotEmpty()) {
            path.moveTo(points[0].x, points[0].y)
            for (i in 1 until points.size) {
                val prev = points[i - 1]
                val curr = points[i]
                val controlX1 = prev.x + (curr.x - prev.x) * 0.4f
                val controlX2 = prev.x + (curr.x - prev.x) * 0.6f
                path.cubicTo(controlX1, prev.y, controlX2, curr.y, curr.x, curr.y)
            }
        }

        // Gradient stroke
        val chartBrush = Brush.horizontalGradient(
            colors = listOf(ChartCool, ChartWarm),
            startX = 0f,
            endX = width
        )

        drawPath(
            path = path,
            brush = chartBrush,
            style = Stroke(
                width = 3.dp.toPx(),
                cap = StrokeCap.Round
            )
        )

        // Draw dot + dashed line at current hour
        val nowIndex = hourlyPoints.indexOfFirst { it.isNow }
        if (nowIndex >= 0 && nowIndex < points.size) {
            val nowPoint = points[nowIndex]

            // Dashed vertical line from dot down
            val dashEffect = PathEffect.dashPathEffect(
                floatArrayOf(6.dp.toPx(), 4.dp.toPx()),
                0f
            )
            drawLine(
                color = Color.White.copy(alpha = 0.4f),
                start = Offset(nowPoint.x, nowPoint.y),
                end = Offset(nowPoint.x, height),
                strokeWidth = 1.dp.toPx(),
                pathEffect = dashEffect
            )

            // Outer glow circle
            drawCircle(
                color = Color.White.copy(alpha = 0.2f),
                radius = 8.dp.toPx(),
                center = nowPoint
            )

            // White dot
            drawCircle(
                color = Color.White,
                radius = 4.dp.toPx(),
                center = nowPoint
            )
        }
    }
}
