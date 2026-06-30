package com.example.task_2_android_basic.tutorial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * BÀI 1: CÁC BỐ CỤC CƠ BẢN (LAYOUTS) TRONG JETPACK COMPOSE
 *
 * Trong Jetpack Compose, chúng ta không dùng file XML để thiết kế giao diện nữa.
 * Thay vào đó, chúng ta viết code Kotlin để vẽ giao diện (gọi là Declarative UI).
 * Có 3 thành phần bố cục chính để sắp xếp các phần tử:
 * 1. Column: Sắp xếp các phần tử theo chiều dọc (từ trên xuống dưới).
 * 2. Row: Sắp xếp các phần tử theo chiều ngang (từ trái sang phải).
 * 3. Box: Xếp chồng các phần tử lên nhau (giống FrameLayout trong XML).
 */

@Composable
fun ColumnExample() {
    // Column xếp các phần tử theo chiều dọc
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFF3F4F6))
    ) {
        Text(text = "Dòng thứ 1 trong Column", fontSize = 16.sp, color = Color.Black)
        Text(text = "Dòng thứ 2 trong Column", fontSize = 16.sp, color = Color.Blue)
        Text(text = "Dòng thứ 3 trong Column", fontSize = 16.sp, color = Color.Red)
    }
}

@Composable
fun RowExample() {
    // Row xếp các phần tử theo chiều ngang
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFE5E7EB))
    ) {
        Text(text = "Trái  ", fontSize = 16.sp, color = Color.DarkGray)
        Text(text = "Giữa  ", fontSize = 16.sp, color = Color.Magenta)
        Text(text = "Phải", fontSize = 16.sp, color = Color.Blue)
    }
}

@Composable
fun BoxExample() {
    // Box xếp chồng các phần tử lên nhau và cho phép căn chỉnh vị trí linh hoạt
    Box(
        modifier = Modifier
            .size(150.dp)
            .background(Color.LightGray)
    ) {
        // Phần tử nằm ở góc trên bên trái (mặc định)
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color.Red)
        )

        // Phần tử nằm ở chính giữa Box
        Text(
            text = "Giữa Box",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )

        // Phần tử nằm ở góc dưới bên phải
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Blue)
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun Lesson1LayoutsScreen() {
    // Kết hợp cả 3 layout vào một màn hình học tập
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Học Layouts cơ bản",
            fontSize = 24.sp,   
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(text = "1. Column (Chiều dọc):", modifier = Modifier.align(Alignment.Start))
        ColumnExample()

        SpacerHeight(16) // Xem helper bên dưới hoặc dùng Spacer chuẩn

        Text(text = "2. Row (Chiều ngang):", modifier = Modifier.align(Alignment.Start))
        RowExample()

        SpacerHeight(16)

        Text(text = "3. Box (Xếp chồng):", modifier = Modifier.align(Alignment.Start))
        BoxExample()
    }
}

// Hàm bổ trợ vẽ khoảng trống
@Composable
fun SpacerHeight(heightDp: Int) {
    androidx.compose.foundation.layout.Spacer(
        modifier = Modifier.height(heightDp.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun Lesson1Preview() {
    Lesson1LayoutsScreen()
}
