package com.example.task_3_jetpack_compose.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_3_jetpack_compose.data.CategoryEntity
import com.example.task_3_jetpack_compose.data.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: NoteRepository,
) : ViewModel() {
    val categories: StateFlow<List<CategoryEntity>> = repository.categories.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    fun addCategory(name: String) {
        viewModelScope.launch { repository.addCategory(name) }
    }

    fun renameCategory(category: CategoryEntity, name: String) {
        viewModelScope.launch { repository.renameCategory(category, name) }
    }

    fun deleteCategory(category: CategoryEntity) {
        viewModelScope.launch { repository.deleteCategory(category) }
    }
}
