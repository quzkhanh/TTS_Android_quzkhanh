package com.example.task_3_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.task_3_jetpack_compose.ui.AppViewModel
import com.example.task_3_jetpack_compose.ui.home.HomeScreen
import com.example.task_3_jetpack_compose.ui.note.NoteScreen
import com.example.task_3_jetpack_compose.ui.settings.CategoryManagementScreen
import com.example.task_3_jetpack_compose.ui.theme.ThinkTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThinkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { ThinkApp() }
    }
}

@Composable
private fun ThinkApp(viewModel: AppViewModel = hiltViewModel()) {
    val darkMode by viewModel.darkMode.collectAsStateWithLifecycle()

    ThinkTheme(darkTheme = darkMode) {
        val navController = rememberNavController()
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding(),
        ) {
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(
                        onOpenNote = { navController.navigate("note/$it") },
                        onCreateNote = { navController.navigate("note/0") },
                        darkMode = darkMode,
                        onToggleTheme = { viewModel.setDarkMode(!darkMode) },
                    )
                }
                composable(
                    route = "note/{noteId}",
                    arguments = listOf(navArgument("noteId") { type = NavType.LongType }),
                ) { entry ->
                    NoteScreen(
                        noteId = checkNotNull(entry.arguments?.getLong("noteId")),
                        onClose = navController::popBackStack,
                        onManageCategories = { navController.navigate("categories") },
                    )
                }
                composable("categories") {
                    CategoryManagementScreen(
                        onBack = navController::popBackStack,
                    )
                }
            }
        }
    }
}
