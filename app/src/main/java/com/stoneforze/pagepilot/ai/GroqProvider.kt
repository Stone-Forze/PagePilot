package com.stoneforze.pagepilot.ai

class GroqProvider : AiProvider {
    override val name = "Groq"
    override val isAvailable = true

    override suspend fun generateFlashcards(text: String): String {
        return "Flashcards from Groq (Mock)"
    }

    override suspend fun generateSummary(text: String, mode: String): String {
        return "Summary from Groq (Mock) - Mode: $mode"
    }

    override suspend fun generateQuiz(text: String, difficulty: String): String {
        return "Quiz from Groq (Mock) - Difficulty: $difficulty"
    }
}
