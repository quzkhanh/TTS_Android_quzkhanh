package com.example.task_3_jetpack_compose.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.task_3_jetpack_compose.R
import com.example.task_3_jetpack_compose.data.CategoryEntity
import com.example.task_3_jetpack_compose.data.NoteEntity
import com.example.task_3_jetpack_compose.ui.components.ThinkHeader
import com.example.task_3_jetpack_compose.ui.theme.AccentBlack
import com.example.task_3_jetpack_compose.ui.theme.CardPeach
import com.example.task_3_jetpack_compose.ui.theme.Ink

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onOpenNote: (Long) -> Unit,
    onCreateNote: () -> Unit,
    darkMode: Boolean,
    onToggleTheme: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val selectedCategoryId by viewModel.selectedCategoryId.collectAsStateWithLifecycle()
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val notes = viewModel.notes.collectAsLazyPagingItems()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreateNote,
                shape = CircleShape,
                containerColor = AccentBlack,
                contentColor = Color.White,
            ) {
                Icon(Icons.Default.Add, contentDescription = "Tạo ghi chú")
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            ThinkHeader(
                endContent = {
                    IconButton(onClick = onToggleTheme) {
                        Icon(
                            imageVector = if (darkMode) {
                                Icons.Default.LightMode
                            } else {
                                Icons.Default.DarkMode
                            },
                            contentDescription = if (darkMode) {
                                "Bật giao diện sáng"
                            } else {
                                "Bật giao diện tối"
                            },
                        )
                    }
                },
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                placeholder = { Text("Tìm ghi chú") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
            Spacer(Modifier.height(12.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    FilterChip(
                        selected = selectedCategoryId == null,
                        onClick = { viewModel.selectCategory(null) },
                        label = { Text("Tất cả") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AccentBlack,
                            selectedLabelColor = Color.White,
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = selectedCategoryId == null,
                            borderColor = MaterialTheme.colorScheme.outline,
                            selectedBorderColor = AccentBlack,
                        ),
                        shape = RoundedCornerShape(50),
                    )
                }
                items(categories, key = { it.id }) { category ->
                    val selected = selectedCategoryId == category.id
                    FilterChip(
                        selected = selected,
                        onClick = { viewModel.selectCategory(category.id) },
                        label = { Text(category.name) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AccentBlack,
                            selectedLabelColor = Color.White,
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = selected,
                            borderColor = MaterialTheme.colorScheme.outline,
                            selectedBorderColor = AccentBlack,
                        ),
                        shape = RoundedCornerShape(50),
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
            if (notes.loadState.refresh is LoadState.NotLoading && notes.itemCount == 0) {
                EmptyNotes(modifier = Modifier.weight(1f))
            } else {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 88.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalItemSpacing = 12.dp,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(
                        count = notes.itemCount,
                        key = { index -> notes[index]?.id ?: index },
                    ) { index ->
                        notes[index]?.let { note ->
                            NoteCard(
                                note = note,
                                category = categories.firstOrNull { it.id == note.categoryId },
                                onClick = { onOpenNote(note.id) },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NoteCard(
    note: NoteEntity,
    category: CategoryEntity?,
    onClick: () -> Unit,
) {
    val color = category?.let { Color(it.colorArgb) } ?: CardPeach
    Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = note.title.ifBlank { "Ghi chú" },
                color = Ink,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
            )
            if (note.content.isNotBlank()) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = note.content,
                    color = Ink.copy(alpha = 0.72f),
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun EmptyNotes(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_notes))
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(composition = composition, modifier = Modifier.size(150.dp))
            Text(
                text = "Chưa có ghi chú nào",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 13.sp,
            )
        }
    }
}
