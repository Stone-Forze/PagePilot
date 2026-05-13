package com.stoneforze.pagepilot.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "highlights")
data class Highlight(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pdfId: Int,
    val text: String,
    val color: String, // e.g., "Teal", "Gold"
    val pageNumber: Int,
    val note: String? = null
)
