package com.example.task_3_jetpack_compose.ui.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_3_jetpack_compose.data.CategoryEntity
import com.example.task_3_jetpack_compose.data.DefaultCategories
import com.example.task_3_jetpack_compose.data.NoteEntity
import com.example.task_3_jetpack_compose.data.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NoteUiState(
    val id: Long = 0,
    val title: String = "",
    val content: String = "",
    val categoryId: Long = DefaultCategories.OTHER_ID,
    val createdAt: Long = 0,
    val isLoading: Boolean = true,
    val exists: Boolean = false,
)

@HiltViewModel
class NoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: NoteRepository,
) : ViewModel() {
    private val noteId: Long = checkNotNull(savedStateHandle["noteId"])
    private val _state = MutableStateFlow(NoteUiState(isLoading = noteId != 0L))
    val state: StateFlow<NoteUiState> = _state
    val categories: StateFlow<List<CategoryEntity>> = repository.categories.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    private val _closeScreen = Channel<Unit>(Channel.BUFFERED)
    val closeScreen = _closeScreen.receiveAsFlow()

    init {
        if (noteId != 0L) {
            viewModelScope.launch {
                repository.observe(noteId).first()?.let { note ->
                    _state.value = NoteUiState(
                        id = note.id,
                        title = note.title,
                        content = note.content,
                        categoryId = note.categoryId,
                        createdAt = note.createdAt,
                        isLoading = false,
                        exists = true,
                    )
                } ?: run {
                    _state.update { it.copy(isLoading = false) }
                }
            }
        }
        viewModelScope.launch {
            repository.categories.collect { categories ->
                if (noteId == 0L && categories.none { it.id == _state.value.categoryId }) {
                    categories.firstOrNull()?.let { first ->
                        _state.update { it.copy(categoryId = first.id) }
                    }
                }
            }
        }
    }

    fun updateTitle(value: String) = _state.update { it.copy(title = value) }
    fun updateContent(value: String) = _state.update { it.copy(content = value) }
    fun updateCategory(value: Long) = _state.update { it.copy(categoryId = value) }

    fun save() {
        val current = _state.value
        if (current.title.isBlank() && current.content.isBlank()) {
            viewModelScope.launch { _closeScreen.send(Unit) }
            return
        }
        viewModelScope.launch {
            val now = System.currentTimeMillis()
            repository.save(
                NoteEntity(
                    id = current.id,
                    title = current.title.trim(),
                    content = current.content.trim(),
                    categoryId = current.categoryId,
                    createdAt = current.createdAt.takeIf { it != 0L } ?: now,
                    updatedAt = now,
                ),
            )
            _closeScreen.send(Unit)
        }
    }

    fun delete() {
        val current = _state.value
        if (!current.exists) return
        viewModelScope.launch {
            repository.delete(
                NoteEntity(
                    id = current.id,
                    title = current.title,
                    content = current.content,
                    categoryId = current.categoryId,
                    createdAt = current.createdAt,
                    updatedAt = System.currentTimeMillis(),
                ),
            )
            _closeScreen.send(Unit)
        }
    }
}
