package com.stoneforze.pagepilot.ai

interface AiProvider {
    val name: String
    val isAvailable: Boolean

    suspend fun generateFlashcards(text: String): String
    suspend fun generateSummary(text: String, mode: String): String
    suspend fun generateQuiz(text: String, difficulty: String): String
}
