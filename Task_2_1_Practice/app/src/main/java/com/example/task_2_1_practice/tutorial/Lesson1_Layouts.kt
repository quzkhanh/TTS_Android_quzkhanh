package com.example.task_2_1_practice.tutorial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//! Bo cuc layout co ban

@Composable
fun ColumnExample() {
    // sap xep cac phan tu theo chieu doc
    Column(
        modifier = Modifier.fillMaxWidth().safeDrawingPadding()
            .background(Color(0xFFF3F4F6))
    ){
        Text(text = "Dong thu 1 trong Column", fontSize = 16 .sp, color = Color.Black)
        Text(text = "Dong thu 2 trong Column", fontSize = 16 .sp, color = Color.Blue)
        Text(text = "Dong thu 3 trong Column", fontSize = 16 .sp, color = Color.Red)
      }
}

@Composable
fun RowExample() {
    // Sap xep theo chieu ngang
    Row(
        modifier = Modifier.fillMaxWidth().safeDrawingPadding()
            .background(Color(0xFFE5E7EB))

    ) {
        Text(text = "Trái trong Row", fontSize = 16 .sp, color = Color.DarkGray)
        SpacerWidth(5)
        Text(text = "Giữa trong Row", fontSize = 16 .sp, color = Color.Magenta)
        SpacerWidth(5)
        Text(text = "Phải trong Row", fontSize = 16 .sp, color = Color.Blue )
    }
}

@Composable
fun BoxExample(){
    //* Box xep chong cac phan tu len nhau va can chinh vi tri linh hoat
    Box(
        modifier = Modifier.size(150 .dp).background(Color.LightGray)
    ) {
        //? Phan tu nam o goc ben trai (mac dinh)
        Box(
            modifier = Modifier.size(80 .dp).background(Color.Red)
        )
        //? Phan tu nam o chinh giua Box
        Text(
            text = "Giua Box",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )

        //? Phan tu nam o goc duoi ben phia
        Box(
            modifier = Modifier.size(40 .dp)
                .background(Color.Blue)
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
// Ket hop ca 3 layout vao 1 screen
fun LayoutLearning() {
    Column(
        modifier = Modifier.fillMaxSize().safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hoc Layouts co ban",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(text = "1. Column (chieu doc):", modifier = Modifier.align(Alignment.Start))
        ColumnExample()

        SpacerHeight(16)

        Text(text = "2. Row (chieu ngang):", modifier = Modifier.align(Alignment.Start))
        RowExample()

        SpacerHeight(16)

        Text(text = "3. Box (Xep chong):", modifier = Modifier.align(Alignment.Start))
        BoxExample()
    }
}

// Ham bo tro ve khoang trong
@Composable
fun SpacerHeight (heightDp: Int) {
    androidx.compose.foundation.layout.Spacer(
        modifier = Modifier.height(heightDp.dp)
    )
}

@Composable
fun SpacerWidth (WidthDp: Int) {
    androidx.compose.foundation.layout.Spacer(
        modifier = Modifier.width(WidthDp.dp)
    )
}
@Preview(showBackground = true)

@Composable
fun LayoutLearningPreview() {
    LayoutLearning()
}
