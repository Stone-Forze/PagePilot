package com.stoneforze.pagepilot.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pdfs")
data class PdfDocument(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val path: String,
    val lastReadPage: Int = 0,
    val totalPages: Int = 0,
    val addedAt: Long = System.currentTimeMillis()
)
