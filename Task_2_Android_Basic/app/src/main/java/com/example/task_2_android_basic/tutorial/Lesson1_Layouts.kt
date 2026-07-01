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

// * Lesson 1: Basic Layouts in Jetpack Compose (Column, Row, Box)
// ? Unlike XML, Compose uses Declarative UI written in Kotlin.

@Composable
fun ColumnExample() {
    // * Column arranges elements vertically
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
    // * Row arranges elements horizontally
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
    // * Box stacks elements on top of each other
    Box(
        modifier = Modifier
            .size(150.dp)
            .background(Color.LightGray)
    ) {
        // * Top-left aligned child (Default behavior)
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color.Red)
        )

        // * Center aligned text
        Text(
            text = "Giữa Box",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )

        // * Bottom-right aligned child
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
    // ? Combined layouts view for demonstration
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

        SpacerHeight(16)

        Text(text = "2. Row (Chiều ngang):", modifier = Modifier.align(Alignment.Start))
        RowExample()

        SpacerHeight(16)

        Text(text = "3. Box (Xếp chồng):", modifier = Modifier.align(Alignment.Start))
        BoxExample()
    }
}

// * Helper Composable for vertical spacing
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
