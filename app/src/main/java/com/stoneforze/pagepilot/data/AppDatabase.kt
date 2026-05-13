package com.stoneforze.pagepilot.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stoneforze.pagepilot.data.model.Flashcard
import com.stoneforze.pagepilot.data.model.Highlight
import com.stoneforze.pagepilot.data.model.PdfDocument

@Database(entities = [PdfDocument::class, Highlight::class, Flashcard::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pagepilot_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
