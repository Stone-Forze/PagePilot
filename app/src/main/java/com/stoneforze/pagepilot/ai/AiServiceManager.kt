package com.stoneforze.pagepilot.ai

import android.util.Log

class AiServiceManager {
    // Failover logic: Groq -> Gemini -> OpenRouter -> NVIDIA
    private val providers = listOf(
        GroqProvider(),
        GeminiProvider(),
        OpenRouterProvider(),
        NvidiaProvider()
    )

    suspend fun generateFlashcards(text: String): String {
        for (provider in providers) {
            if (provider.isAvailable) {
                try {
                    Log.d("AiManager", "Attempting with ${provider.name}")
                    return provider.generateFlashcards(text)
                } catch (e: Exception) {
                    Log.e("AiManager", "${provider.name} failed. Trying next.", e)
                }
            }
        }
        throw Exception("All AI providers failed.")
    }

    suspend fun generateSummary(text: String, mode: String): String {
        for (provider in providers) {
            if (provider.isAvailable) {
                try {
                    return provider.generateSummary(text, mode)
                } catch (e: Exception) {
                    Log.e("AiManager", "${provider.name} failed. Trying next.", e)
                }
            }
        }
        throw Exception("All AI providers failed.")
    }

    suspend fun generateQuiz(text: String, difficulty: String): String {
         for (provider in providers) {
            if (provider.isAvailable) {
                try {
                    return provider.generateQuiz(text, difficulty)
                } catch (e: Exception) {
                    Log.e("AiManager", "${provider.name} failed. Trying next.", e)
                }
            }
        }
        throw Exception("All AI providers failed.")
    }
}
