package com.example.task_3_jetpack_compose.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.task_3_jetpack_compose.data.CategoryDao
import com.example.task_3_jetpack_compose.data.NoteDao
import com.example.task_3_jetpack_compose.data.NoteRepository
import com.example.task_3_jetpack_compose.data.RoomNoteRepository
import com.example.task_3_jetpack_compose.data.ThinkDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNoteRepository(implementation: RoomNoteRepository): NoteRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ThinkDatabase =
        Room.databaseBuilder(context, ThinkDatabase::class.java, "think.db")
            .addMigrations(ThinkDatabase.MIGRATION_1_2)
            .addCallback(
                object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        ThinkDatabase.seedCategories(db)
                    }
                },
            )
            .build()

    @Provides
    fun provideNoteDao(database: ThinkDatabase): NoteDao = database.noteDao()

    @Provides
    fun provideCategoryDao(database: ThinkDatabase): CategoryDao = database.categoryDao()
}
