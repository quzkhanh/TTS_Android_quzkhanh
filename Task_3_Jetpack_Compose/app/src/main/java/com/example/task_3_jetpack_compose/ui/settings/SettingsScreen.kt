package com.example.task_3_jetpack_compose.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.task_3_jetpack_compose.data.CategoryEntity
import com.example.task_3_jetpack_compose.ui.components.ThinkHeader

@Composable
fun CategoryManagementScreen(
    onBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    var newCategoryName by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        ThinkHeader(startAction = "Quay lại", onStartAction = onBack)
        Text(
            text = "Danh mục ghi chú",
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = newCategoryName,
                onValueChange = { newCategoryName = it },
                singleLine = true,
                placeholder = { Text("Tên danh mục") },
                modifier = Modifier.weight(1f),
            )
            TextButton(
                onClick = {
                    viewModel.addCategory(newCategoryName)
                    newCategoryName = ""
                },
                enabled = newCategoryName.isNotBlank(),
            ) {
                Text("Thêm")
            }
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                start = 20.dp,
                end = 12.dp,
                bottom = 24.dp,
            ),
        ) {
            items(categories, key = { it.id }) { category ->
                CategoryRow(
                    category = category,
                    onRename = { viewModel.renameCategory(category, it) },
                    onDelete = { viewModel.deleteCategory(category) },
                )
            }
        }
    }
}

@Composable
private fun CategoryRow(
    category: CategoryEntity,
    onRename: (String) -> Unit,
    onDelete: () -> Unit,
) {
    var name by rememberSaveable(category.id, category.name) {
        mutableStateOf(category.name)
    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                singleLine = true,
                modifier = Modifier.weight(1f),
            )
            TextButton(
                onClick = { onRename(name) },
                enabled = name.isNotBlank() && name.trim() != category.name,
            ) {
                Text("Lưu")
            }
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Xóa danh mục",
                )
            }
        }
        Spacer(Modifier.height(8.dp))
    }
}
