package com.example.task_2_android_basic.tutorial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * BÀI 4: CÁC THÀNH PHẦN GIAO DIỆN PHỔ BIẾN (COMMON COMPONENTS & LAZY LISTS)
 *
 * Trong Jetpack Compose (đặc biệt là hệ sinh thái Material 3):
 * 1. Text: Hiển thị văn bản, tuỳ biến font chữ, màu sắc, độ đậm/nhạt.
 * 2. Button: Các nút bấm (Button, OutlinedButton, TextButton).
 * 3. Icon/Image: Hiển thị hình ảnh hoặc icon vector từ hệ thống.
 * 4. Card: Khung chứa thông tin được nổi khối nhẹ lên bề mặt giao diện.
 * 5. LazyColumn: Hiển thị danh sách cuộn mượt mà (Tương tự RecyclerView nhưng ngắn gọn, dễ code gấp 10 lần!).
 */

@Composable
fun ComponentsBasicsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Học các thành phần Material 3",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // 1. Dùng Card & Typography
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFECEFF1)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Thành phần: Card",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                Text(
                    text = "Bên trong Card có thể chứa bất kỳ Composable nào, bao gồm Text, Row, Image...",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        // 2. Buttons & Icons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nút bấm thường
            Button(onClick = { /* xử lý */ }) {
                Text("Standard")
            }

            // Nút viền ngoài
            OutlinedButton(onClick = { /* xử lý */ }) {
                Text("Outlined")
            }

            // Icon hình tim
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Yêu thích",
                tint = Color.Red,
                modifier = Modifier.size(32.dp)
            )
        }

        SpacerHeight(16)

        // 3. Giới thiệu về LazyColumn (Danh sách cuộn)
        Text(
            text = "3. LazyColumn (Tương tự RecyclerView):",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        // Dữ liệu mẫu
        val fruits = listOf("Táo (Apple)", "Chuối (Banana)", "Cam (Orange)", "Dâu tây (Strawberry)", "Xoài (Mango)")

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Sử dụng hết phần diện tích còn lại của màn hình
                .padding(top = 8.dp),
            contentPadding = PaddingValues(vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Cú pháp render danh sách
            items(fruits) { fruit ->
                FruitItemRow(fruitName = fruit)
            }
        }
    }
}

@Composable
fun FruitItemRow(fruitName: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFFFB300),
                modifier = Modifier.padding(end = 12.dp)
            )
            Text(
                text = fruitName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Lesson4Preview() {
    ComponentsBasicsScreen()
}
