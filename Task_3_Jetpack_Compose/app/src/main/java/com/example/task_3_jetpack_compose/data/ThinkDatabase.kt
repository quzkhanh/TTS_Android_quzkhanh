package com.example.task_3_jetpack_compose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [NoteEntity::class, CategoryEntity::class],
    version = 2,
    exportSchema = true,
)
abstract class ThinkDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "CREATE TABLE IF NOT EXISTS categories " +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "name TEXT NOT NULL, colorArgb INTEGER NOT NULL)",
                )
                seedCategories(db)
                db.execSQL(
                    "CREATE TABLE notes_new (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "title TEXT NOT NULL, content TEXT NOT NULL, " +
                        "categoryId INTEGER NOT NULL, createdAt INTEGER NOT NULL, " +
                        "updatedAt INTEGER NOT NULL)",
                )
                db.execSQL(
                    "INSERT INTO notes_new " +
                        "(id, title, content, categoryId, createdAt, updatedAt) " +
                        "SELECT id, title, content, " +
                        "CASE category " +
                        "WHEN 'IMPORTANT' THEN 1 WHEN 'LECTURE' THEN 2 " +
                        "WHEN 'TODO' THEN 3 WHEN 'SHOPPING' THEN 4 ELSE 5 END, " +
                        "createdAt, updatedAt FROM notes",
                )
                db.execSQL("DROP TABLE notes")
                db.execSQL("ALTER TABLE notes_new RENAME TO notes")
            }
        }

        fun seedCategories(db: SupportSQLiteDatabase) {
            db.execSQL(
                "INSERT OR IGNORE INTO categories(id, name, colorArgb) VALUES " +
                    "(1, 'Quan trọng', 4294955995), " +
                    "(2, 'Bài giảng', 4292277203), " +
                    "(3, 'Việc cần làm', 4293123579), " +
                    "(4, 'Mua sắm', 4294963896), " +
                    "(5, 'Ghi chú', 4294960071)",
            )
        }
    }
}
