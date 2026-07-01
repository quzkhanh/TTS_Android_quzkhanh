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

// * Lesson 3: State and Recomposition in Jetpack Compose
// ? UI = f(State). UI automatically recomposes when State changes.
// ! Key functions: mutableStateOf() for observability, remember {} to retain value across recompositions.

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

        // * 1. Click counter example
        Text(
            text = "1. Ví dụ Bộ đếm (Counter):",
            modifier = Modifier.align(Alignment.Start)
        )

        var clickCount by remember { mutableStateOf(0) }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Button(
                onClick = { clickCount++ },
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

        // * 2. Real-time text input example
        Text(
            text = "2. Nhập chữ hiển thị ngay lập tức:",
            modifier = Modifier.align(Alignment.Start)
        )

        var nameInput by remember { mutableStateOf("") }

        OutlinedTextField(
            value = nameInput,
            onValueChange = { newValue -> nameInput = newValue },
            label = { Text("Nhập tên của bạn") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            singleLine = true
        )

        if (nameInput.isNotEmpty()) {
            Text(
                text = "Xin chào, $nameInput!",
                fontSize = 18.sp,
                color = androidx.compose.ui.graphics.Color(0xFF00796B)
            )
        } else {
            Text(
                text = "(Chưa nhập tên)",
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
