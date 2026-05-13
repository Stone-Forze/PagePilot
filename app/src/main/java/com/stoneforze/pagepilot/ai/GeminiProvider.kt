package com.stoneforze.pagepilot.ai

class GeminiProvider : AiProvider {
    override val name = "Gemini"
    override val isAvailable = true

    override suspend fun generateFlashcards(text: String): String {
        return "Flashcards from Gemini (Mock)"
    }

    override suspend fun generateSummary(text: String, mode: String): String {
        return "Summary from Gemini (Mock) - Mode: $mode"
    }

    override suspend fun generateQuiz(text: String, difficulty: String): String {
        return "Quiz from Gemini (Mock) - Difficulty: $difficulty"
    }
}
