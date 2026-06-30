package com.example.task_2_1_practice.tutorial

import android.R
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
import java.net.CookieStore

@Composable
fun ModifierBasicsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hoc Modifier co ban",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // 1. Box bo goc tron (circle) bang cach dung clip (CirleShape)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(100.dp)
                .clip(CircleShape)
                .background(Color(0xFF6200EE))
                .clickable {
                    // xu ly su kien click o day
                    println("Da click vao hinh tron!")
                }
        ) {
            Text(text = "Click me!", color = Color.White)
        }

        // 2. Minh hoa su quan trong cua thu tu trong Modifier
        Text(
            text = "2. Thu tu goi Modifier rat quan trong: ",
            modifier = Modifier.align(Alignment.Start)

        )

        // vi du A: Backgroud truoc, Padding sau (Mau nen chua ca padding)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFB74D), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        )
        {
            Text(text = "A. Background -> Pading (Mau bao quanh chu)")

            SpacerHeight(12)
        }

        //vi du B: Padding truoc, Background sau (Mau nen hien thi phan chu, ngoai ria co padding)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFFFFB74D), shape = RoundedCornerShape(8.dp))
        ) {
            Text(text = "B. Padding -> Background (Nhìn khoảng viền trắng xung quanh)")
        }

        SpacerHeight(24)

        //3. Su dung Border vien ngoai
        Text(
            text = "3. Thêm viền (Border): ",
            modifier = Modifier.align(Alignment.Start)
        )
        Box(
            modifier = Modifier
                .size(width = 200.dp, height = 60 .dp)
                .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(12.dp))
                .padding(8.dp),
            contentAlignment = Alignment.Center

        ) {
            Text("Viền đỏ bo góc")
        }


    }
}

@Preview(showBackground = true)

@Composable
fun Lesson2Preview() {
    ModifierBasicsScreen()
}
