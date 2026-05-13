package com.stoneforze.pagepilot.ai

class NvidiaProvider : AiProvider {
    override val name = "NVIDIA"
    override val isAvailable = true

    override suspend fun generateFlashcards(text: String): String {
        return "Flashcards from NVIDIA (Mock)"
    }

    override suspend fun generateSummary(text: String, mode: String): String {
        return "Summary from NVIDIA (Mock) - Mode: $mode"
    }

    override suspend fun generateQuiz(text: String, difficulty: String): String {
        return "Quiz from NVIDIA (Mock) - Difficulty: $difficulty"
    }
}
