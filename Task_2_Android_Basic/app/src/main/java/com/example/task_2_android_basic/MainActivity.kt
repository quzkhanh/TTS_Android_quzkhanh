package com.example.task_2_android_basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.task_2_android_basic.ui.theme.Task_2_Android_BasicTheme
import com.example.task_2_android_basic.tutorial.LearningDashboardApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task_2_Android_BasicTheme {
                LearningDashboardApp()
            }
        }
    }
}