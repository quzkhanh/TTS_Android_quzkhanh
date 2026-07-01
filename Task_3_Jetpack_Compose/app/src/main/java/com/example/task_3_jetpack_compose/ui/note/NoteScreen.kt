package com.example.task_3_jetpack_compose.ui.note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.task_3_jetpack_compose.data.CategoryEntity
import com.example.task_3_jetpack_compose.ui.components.ThinkHeader
import com.example.task_3_jetpack_compose.ui.theme.AccentBlack
import com.example.task_3_jetpack_compose.ui.theme.HighlightYellow

@Composable
fun NoteScreen(
    noteId: Long,
    onClose: () -> Unit,
    onManageCategories: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    var isEditing by rememberSaveable(noteId) { mutableStateOf(noteId == 0L) }

    LaunchedEffect(viewModel) {
        viewModel.closeScreen.collect { onClose() }
    }

    BackHandler(enabled = isEditing) {
        if (state.exists) isEditing = false else onClose()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        if (isEditing) {
            ThinkHeader()
            EditActions(
                onCancel = {
                    if (state.exists) isEditing = false else onClose()
                },
                onSave = viewModel::save,
            )
        } else {
            ThinkHeader(
                startContent = {
                    IconButton(onClick = onClose) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Quay lại",
                        )
                    }
                },
                endContent = {
                    IconButton(onClick = viewModel::delete) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Xóa",
                        )
                    }
                },
            )
        }

        if (state.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(28.dp))
            }
        } else if (isEditing) {
            EditNote(
                state = state,
                categories = categories,
                onTitleChange = viewModel::updateTitle,
                onContentChange = viewModel::updateContent,
                onCategoryChange = viewModel::updateCategory,
                onManageCategories = onManageCategories,
            )
        } else {
            NoteDetail(
                state = state,
                category = categories.firstOrNull { it.id == state.categoryId },
                onEdit = { isEditing = true },
            )
        }
    }
}

@Composable
private fun EditActions(
    onCancel: () -> Unit,
    onSave: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(onClick = onCancel) { Text("Hủy") }
        TextButton(onClick = onSave) { Text("Lưu") }
    }
}

@Composable
private fun NoteDetail(
    state: NoteUiState,
    category: CategoryEntity?,
    onEdit: () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp),
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onEdit),
    ) {
        Column(Modifier.padding(horizontal = 22.dp, vertical = 12.dp)) {
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 4.dp)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.outline)
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(Modifier.height(28.dp))
            Text(
                text = state.title.ifBlank { "Ghi chú" },
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = category?.name.orEmpty(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
            )
            if (state.content.isNotBlank()) {
                Spacer(Modifier.height(22.dp))
                Text(
                    text = state.content,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 16.sp,
                    lineHeight = 25.sp,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditNote(
    state: NoteUiState,
    categories: List<CategoryEntity>,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onCategoryChange: (Long) -> Unit,
    onManageCategories: () -> Unit,
) {
    val selectionColors = TextSelectionColors(
        handleColor = AccentBlack,
        backgroundColor = HighlightYellow,
    )
    var categoryMenuExpanded by rememberSaveable { mutableStateOf(false) }
    val selectedCategory = categories.firstOrNull { it.id == state.categoryId }

    Column(Modifier.fillMaxSize()) {
        ExposedDropdownMenuBox(
            expanded = categoryMenuExpanded,
            onExpandedChange = { categoryMenuExpanded = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            OutlinedTextField(
                value = selectedCategory?.name.orEmpty(),
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                label = { Text("Danh mục") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryMenuExpanded)
                },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true)
                    .fillMaxWidth(),
            )
            ExposedDropdownMenu(
                expanded = categoryMenuExpanded,
                onDismissRequest = { categoryMenuExpanded = false },
                modifier = Modifier.heightIn(max = 280.dp),
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category.name) },
                        onClick = {
                            onCategoryChange(category.id)
                            categoryMenuExpanded = false
                        },
                    )
                }
                HorizontalDivider()
                DropdownMenuItem(
                    text = { Text("Quản lý danh mục") },
                    onClick = {
                        categoryMenuExpanded = false
                        onManageCategories()
                    },
                )
            }
        }
        CompositionLocalProvider(LocalTextSelectionColors provides selectionColors) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
            ) {
                BasicTextField(
                    value = state.title,
                    onValueChange = onTitleChange,
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 24.sp,
                        lineHeight = 31.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    decorationBox = { inner ->
                        Box {
                            if (state.title.isEmpty()) {
                                Text(
                                    "Tiêu đề",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            inner()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(12.dp))
                BasicTextField(
                    value = state.content,
                    onValueChange = onContentChange,
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        lineHeight = 25.sp,
                    ),
                    decorationBox = { inner ->
                        Box {
                            if (state.content.isEmpty()) {
                                Text(
                                    "Nội dung",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 16.sp,
                                )
                            }
                            inner()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                )
            }
        }
    }
}
