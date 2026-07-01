package com.example.task_3_jetpack_compose.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Ink = Color(0xFF1C1C1E)
val AccentBlack = Color(0xFF111114)
val TextSecondary = Color(0xFF6B6B70)
val SurfaceMuted = Color(0xFFF5F5F7)
val BorderSubtle = Color(0xFFECECEE)
val CardPink = Color(0xFFFFD3DB)
val CardLavender = Color(0xFFE3DDFB)
val CardYellow = Color(0xFFFFF2B8)
val CardGreen = Color(0xFFD6F3D3)
val CardPeach = Color(0xFFFFE3C7)
val HighlightYellow = Color(0xB3FCE38A)

private val ThinkLightColors = lightColorScheme(
    primary = AccentBlack,
    onPrimary = Color.White,
    background = Color.White,
    onBackground = Ink,
    surface = Color.White,
    onSurface = Ink,
    surfaceVariant = SurfaceMuted,
    onSurfaceVariant = TextSecondary,
    outline = BorderSubtle,
)

private val ThinkDarkColors = darkColorScheme(
    primary = Color(0xFFF1F1F4),
    onPrimary = Color(0xFF242429),
    background = Color(0xFF242429),
    onBackground = Color(0xFFF4F4F6),
    surface = Color(0xFF303037),
    onSurface = Color(0xFFF4F4F6),
    surfaceVariant = Color(0xFF3A3A42),
    onSurfaceVariant = Color(0xFFD0D0D6),
    outline = Color(0xFF575760),
)

@Composable
fun ThinkTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) ThinkDarkColors else ThinkLightColors,
        typography = Typography,
        content = content,
    )
}
