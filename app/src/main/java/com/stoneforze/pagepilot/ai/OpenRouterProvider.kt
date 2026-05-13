package com.stoneforze.pagepilot.ai

class OpenRouterProvider : AiProvider {
    override val name = "OpenRouter"
    override val isAvailable = true

    override suspend fun generateFlashcards(text: String): String {
        return "Flashcards from OpenRouter (Mock)"
    }

    override suspend fun generateSummary(text: String, mode: String): String {
        return "Summary from OpenRouter (Mock) - Mode: $mode"
    }

    override suspend fun generateQuiz(text: String, difficulty: String): String {
        return "Quiz from OpenRouter (Mock) - Difficulty: $difficulty"
    }
}
