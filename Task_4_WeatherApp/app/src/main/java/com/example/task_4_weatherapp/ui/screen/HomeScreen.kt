package com.example.task_4_weatherapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.task_4_weatherapp.ui.components.ErrorCard
import com.example.task_4_weatherapp.ui.components.ShimmerCard
import com.example.task_4_weatherapp.ui.components.WeatherCard
import com.example.task_4_weatherapp.ui.theme.ScreenBg
import com.example.task_4_weatherapp.ui.viewmodel.CardUiState
import com.example.task_4_weatherapp.ui.viewmodel.WeatherViewModel
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(
    viewModel: WeatherViewModel = viewModel()
) {
    val cardStates by viewModel.cardStates.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBg)
    ) {
        if (cardStates.isNotEmpty()) {
            val pagerState = rememberPagerState(pageCount = { cardStates.size })

            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 24.dp),
                pageSpacing = 16.dp,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val pageOffset = (
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                ).absoluteValue

                val scale = lerp(
                    start = 0.92f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
                val alpha = lerp(
                    start = 0.7f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )

                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            this.alpha = alpha
                        }
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    when (val state = cardStates[page]) {
                        is CardUiState.Loading -> {
                            ShimmerCard(
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        is CardUiState.Success -> {
                            WeatherCard(
                                data = state.data,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        is CardUiState.Error -> {
                            ErrorCard(
                                message = state.message,
                                onRetry = { viewModel.retryCity(page) },
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}
