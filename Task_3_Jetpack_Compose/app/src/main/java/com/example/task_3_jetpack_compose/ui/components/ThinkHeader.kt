package com.example.task_3_jetpack_compose.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ThinkHeader(
    modifier: Modifier = Modifier,
    startAction: String? = null,
    onStartAction: () -> Unit = {},
    startContent: (@Composable () -> Unit)? = null,
    endAction: String? = null,
    onEndAction: () -> Unit = {},
    endContent: (@Composable () -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (startContent != null) {
            Box(
                modifier = Modifier.align(Alignment.CenterStart),
                contentAlignment = Alignment.Center,
            ) {
                startContent()
            }
        } else if (startAction != null) {
            TextButton(
                onClick = onStartAction,
                modifier = Modifier.align(Alignment.CenterStart),
            ) { Text(startAction) }
        }
        Text(
            text = "THINK.",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 4.sp,
        )
        if (endContent != null) {
            Box(
                modifier = Modifier.align(Alignment.CenterEnd),
                contentAlignment = Alignment.Center,
            ) {
                endContent()
            }
        } else if (endAction != null) {
            TextButton(
                onClick = onEndAction,
                modifier = Modifier.align(Alignment.CenterEnd),
            ) { Text(endAction) }
        }
    }
}
