package com.example.task_2_android_basic.tutorial

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * BÀI 3: STATE VÀ RECOMPOSITION TRONG JETPACK COMPOSE
 *
 * Trong Jetpack Compose, giao diện được vẽ dựa trên Dữ liệu (State).
 * Công thức cốt lõi: UI = f(State) - Nghĩa là UI thay đổi khi State thay đổi.
 *
 * Hai từ khóa vô cùng quan trọng:
 * 1. mutableStateOf(value): Tạo ra một biến trạng thái có thể quan sát (observable state).
 *    Khi giá trị này thay đổi, Compose sẽ tự động vẽ lại các phần tử liên quan (gọi là Recomposition).
 *
 * 2. remember { ... }: Giữ lại giá trị của biến qua các lần vẽ lại (recomposition). Nếu không có `remember`,
 *    biến đó sẽ bị khởi tạo lại về giá trị ban đầu mỗi khi màn hình vẽ lại.
 *
 * 3. Delegation 'by' (dùng import androidx.compose.runtime.getValue/setValue):
 *    Giúp đọc ghi trực tiếp biến mà không cần dùng tên_biến.value.
 */

@Composable
fun StateBasicsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Học State cơ bản",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Ví dụ 1: Bộ đếm số lần Click (Counter Button)
        Text(
            text = "1. Ví dụ Bộ đếm (Counter):",
            modifier = Modifier.align(Alignment.Start)
        )

        // Khai báo state
        var clickCount by remember { mutableStateOf(0) }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Button(
                onClick = { clickCount++ }, // Mỗi lần click tăng clickCount lên 1
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Text(text = "Click me!")
            }

            Text(
                text = "Bạn đã click: $clickCount lần",
                fontSize = 18.sp
            )
        }

        SpacerHeight(24)

        // Ví dụ 2: Nhập text cập nhật giao diện thời gian thực (Real-time Input Text)
        Text(
            text = "2. Nhập chữ hiển thị ngay lập tức:",
            modifier = Modifier.align(Alignment.Start)
        )

        // Khai báo state lưu chữ người dùng nhập
        var nameInput by remember { mutableStateOf("") }
            
        OutlinedTextField(
            value = nameInput,
            onValueChange = { newValue -> nameInput = newValue }, // Khi chữ thay đổi thì cập nhật state
            label = { Text("Nhập tên của bạn") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            singleLine = true
        )

        if (nameInput.isNotEmpty()) {
            Text(
                text = "Xin chào, $nameInput! Chúc bạn học Compose vui vẻ!",
                fontSize = 18.sp,
                color = androidx.compose.ui.graphics.Color(0xFF00796B)
            )
        } else {
            Text(
                text = "(Hãy nhập tên ở ô phía trên)",
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Lesson3Preview() {
    StateBasicsScreen()
}
