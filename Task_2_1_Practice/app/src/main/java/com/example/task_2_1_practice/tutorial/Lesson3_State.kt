package com.example.task_2_1_practice.tutorial

import androidx.compose.foundation.basicMarquee
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
fun StateBasicScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hoc State co ban", fontSize = 24.sp, modifier = Modifier.padding(bottom = 20.dp)
        )

        // vi du 1: bo dem so lan Click (Counter Button)
        Text(
            text = "1. Vi du Bo dem (counter):", modifier = Modifier.align(Alignment.Start)
        )

        // Khai bao state
        var clickCount by remember { mutableStateOf(0) }

        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(end = 12.dp)
        ) {
            Button(
                onClick = { clickCount++ }, // moi click tang clickCount
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Text(text = "Click Me!")
            }

            Text(
                text = "Ban da click: $clickCount lan", fontSize = 18.sp
            )
        }

        SpacerHeight(24)

        // vi du 2: Nhap text cap nhat giao dien theo thoi gian thuc (real tinme input text)
        Text(
            text = "2. Nhap chu hien thi ngay lap tuc:", modifier = Modifier.align(Alignment.Start)
        )

        // Khai bao state luu input nguoi dung nhap
        var inputText by remember { mutableStateOf("") }

        OutlinedTextField(
            value = inputText,
            onValueChange = { newValue -> inputText = newValue },
            label = { Text("Nhap input vao day") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            singleLine = true
        )

        if (inputText.isNotEmpty()) {
            Text(text = "Ban da nhap: $inputText", fontSize = 18.sp)
        } else {
            Text(text = "Ban chua nhap gi", fontSize = 18.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StateBasicScreenPreview() {
    StateBasicScreen()
}














