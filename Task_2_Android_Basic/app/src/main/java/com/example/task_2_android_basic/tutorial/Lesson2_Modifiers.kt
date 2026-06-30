package com.example.task_2_android_basic.tutorial

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// * Lesson 2: Modifiers in Jetpack Compose
// ? Modifiers decorate or configure composables (sizing, padding, clicks, borders, etc.)
// ! IMPORTANT: The order of calling modifier functions matters!

@Composable
fun ModifierBasicsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Học Modifiers cơ bản",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // * 1. Round shape clip and click listener
        Text(
            text = "1. Cắt hình tròn & click sự kiện:",
            modifier = Modifier.align(Alignment.Start)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color(0xFF6200EE))
                .clickable {
                    println("Đã click vào hình tròn!")
                }
        ) {
            Text(text = "Click me!", color = Color.White)
        }

        SpacerHeight(24)

        // * 2. Demonstrating modifier chain order
        Text(
            text = "2. Thứ tự gọi Modifier rất quan trọng:",
            modifier = Modifier.align(Alignment.Start)
        )

        // ? Example A: Background -> Padding (Color covers padding area)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFB74D), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text("A: Background -> Padding (Màu bao quanh chữ)")
        }

        SpacerHeight(12)

        // ? Example B: Padding -> Background (Padding acts as outer margins)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFFFFB74D), shape = RoundedCornerShape(8.dp))
        ) {
            Text("B: Padding -> Background (Nhìn khoảng viền trống xung quanh)")
        }

        SpacerHeight(24)

        // * 3. Adding borders
        Text(
            text = "3. Thêm viền (Border):",
            modifier = Modifier.align(Alignment.Start)
        )
        Box(
            modifier = Modifier
                .size(width = 200.dp, height = 60.dp)
                .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(12.dp))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Viền đỏ bo góc 12dp")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Lesson2Preview() {
    ModifierBasicsScreen()
}
