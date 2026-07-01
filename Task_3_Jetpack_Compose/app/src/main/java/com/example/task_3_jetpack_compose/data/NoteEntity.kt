package com.example.task_3_jetpack_compose.data

import androidx.room.Entity
import androidx.room.PrimaryKey

object DefaultCategories {
    const val IMPORTANT_ID = 1L
    const val LECTURE_ID = 2L
    const val TODO_ID = 3L
    const val SHOPPING_ID = 4L
    const val OTHER_ID = 5L

    val colors = listOf(
        0xFFFFD3DB.toInt(),
        0xFFD6F3D3.toInt(),
        0xFFE3DDFB.toInt(),
        0xFFFFF2B8.toInt(),
        0xFFFFE3C7.toInt(),
    )
}

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val colorArgb: Int,
)

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val content: String,
    val categoryId: Long = DefaultCategories.OTHER_ID,
    val createdAt: Long,
    val updatedAt: Long,
)
