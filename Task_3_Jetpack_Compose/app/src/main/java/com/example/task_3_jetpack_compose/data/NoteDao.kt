package com.example.task_3_jetpack_compose.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY updatedAt DESC")
    fun pagingSource(): PagingSource<Int, NoteEntity>

    @Query("SELECT * FROM notes WHERE categoryId = :categoryId ORDER BY updatedAt DESC")
    fun pagingSource(categoryId: Long): PagingSource<Int, NoteEntity>

    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    fun observe(id: Long): Flow<NoteEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity): Long

    @Update
    suspend fun update(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)

    @Query("UPDATE notes SET categoryId = :replacementId WHERE categoryId = :categoryId")
    suspend fun replaceCategory(categoryId: Long, replacementId: Long)
}

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories ORDER BY id")
    fun observeAll(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories ORDER BY id")
    suspend fun getAll(): List<CategoryEntity>

    @Insert
    suspend fun insert(category: CategoryEntity): Long

    @Update
    suspend fun update(category: CategoryEntity)

    @Query("DELETE FROM categories WHERE id = :id")
    suspend fun delete(id: Long)
}
