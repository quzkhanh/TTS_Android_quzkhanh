package com.example.task_3_jetpack_compose.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.withTransaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NoteRepository {
    val categories: Flow<List<CategoryEntity>>
    fun notes(categoryId: Long?, query: String): Flow<PagingData<NoteEntity>>
    fun observe(id: Long): Flow<NoteEntity?>
    suspend fun save(note: NoteEntity): Long
    suspend fun delete(note: NoteEntity)
    suspend fun addCategory(name: String)
    suspend fun renameCategory(category: CategoryEntity, name: String)
    suspend fun deleteCategory(category: CategoryEntity)
}

class RoomNoteRepository @Inject constructor(
    private val database: ThinkDatabase,
    private val noteDao: NoteDao,
    private val categoryDao: CategoryDao,
) : NoteRepository {
    override val categories: Flow<List<CategoryEntity>> = categoryDao.observeAll()

    override fun notes(categoryId: Long?, query: String): Flow<PagingData<NoteEntity>> =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            when {
                categoryId == null && query.isBlank() -> noteDao.pagingSource()
                categoryId == null -> noteDao.pagingSource(query)
                query.isBlank() -> noteDao.pagingSource(categoryId)
                else -> noteDao.pagingSource(categoryId, query)
            }
        }.flow

    override fun observe(id: Long): Flow<NoteEntity?> = noteDao.observe(id)

    override suspend fun save(note: NoteEntity): Long {
        if (note.id == 0L) return noteDao.insert(note)
        noteDao.update(note)
        return note.id
    }

    override suspend fun delete(note: NoteEntity) = noteDao.delete(note)

    override suspend fun addCategory(name: String) {
        val cleanName = name.trim()
        if (cleanName.isEmpty()) return
        val colorIndex = categoryDao.getAll().size % DefaultCategories.colors.size
        categoryDao.insert(
            CategoryEntity(
                name = cleanName,
                colorArgb = DefaultCategories.colors[colorIndex],
            ),
        )
    }

    override suspend fun renameCategory(category: CategoryEntity, name: String) {
        val cleanName = name.trim()
        if (cleanName.isNotEmpty()) categoryDao.update(category.copy(name = cleanName))
    }

    override suspend fun deleteCategory(category: CategoryEntity) {
        database.withTransaction {
            val replacement = categoryDao.getAll().firstOrNull { it.id != category.id }
                ?: CategoryEntity(
                    id = categoryDao.insert(
                        CategoryEntity(
                            name = "Ghi chú",
                            colorArgb = DefaultCategories.colors.last(),
                        ),
                    ),
                    name = "Ghi chú",
                    colorArgb = DefaultCategories.colors.last(),
                )
            noteDao.replaceCategory(category.id, replacement.id)
            categoryDao.delete(category.id)
        }
    }
}
