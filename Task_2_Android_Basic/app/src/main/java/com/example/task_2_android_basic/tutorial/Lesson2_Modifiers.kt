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

/**
 * BÀI 2: MODIFIERS TRONG JETPACK COMPOSE
 *
 * Modifier là một tập hợp các thuộc tính giúp thay đổi diện mạo, hành vi, kích thước
 * và cách thức tương tác của các Composable Component.
 *
 * Các Modifier phổ biến:
 * - size(), width(), height(): Định kích thước.
 * - padding(): Tạo khoảng lề.
 * - background(): Đặt màu nền hoặc gradient.
 * - border(): Tạo viền xung quanh phần tử.
 * - clip(): Bo góc hoặc cắt phần tử theo hình dạng cụ thể (ví dụ: CircleShape).
 * - clickable(): Thêm sự kiện click chuột/chạm tay.
 *
 * *LƯU Ý QUAN TRỌNG*: Thứ tự gọi các hàm trong Modifier rất quan trọng!
 * Ví dụ:
 * - Modifier.padding(16.dp).background(Color.Red): Khoảng cách padding sẽ nằm ngoài vùng màu đỏ.
 * - Modifier.background(Color.Red).padding(16.dp): Khoảng cách padding sẽ nằm trong vùng màu đỏ.
 */

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

        // 1. Box bo góc tròn (Circle) bằng cách dùng clip(CircleShape)
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
                    // Xử lý sự kiện click ở đây
                    println("Đã click vào hình tròn!")
                }
        ) {
            Text(text = "Click me!", color = Color.White)
        }

        SpacerHeight(24)

        // 2. Minh họa sự quan trọng của thứ tự trong Modifier
        Text(
            text = "2. Thứ tự gọi Modifier rất quan trọng:",
            modifier = Modifier.align(Alignment.Start)
        )

        // Ví dụ A: Background trước, Padding sau (Màu nền chứa cả padding)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFB74D), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text("A: Background -> Padding (Màu bao quanh chữ)")
        }

        SpacerHeight(12)

        // Ví dụ B: Padding trước, Background sau (Màu nền chỉ ở phần chữ, ngoài rìa có padding)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFFFFB74D), shape = RoundedCornerShape(8.dp))
        ) {
            Text("B: Padding -> Background (Nhìn khoảng viền trống xung quanh)")
        }

        SpacerHeight(24)

        // 3. Sử dụng border viền ngoài
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
