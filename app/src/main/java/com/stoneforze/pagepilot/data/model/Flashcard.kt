package com.stoneforze.pagepilot.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flashcards")
data class Flashcard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val highlightId: Int?, // Optional, if generated from a highlight
    val pdfId: Int,
    val question: String,
    val answer: String,
    val type: String, // e.g., "Basic Q&A", "Definition"
    val isWeak: Boolean = false
)
