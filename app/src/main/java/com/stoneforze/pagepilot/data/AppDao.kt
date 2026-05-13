package com.stoneforze.pagepilot.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.stoneforze.pagepilot.data.model.Flashcard
import com.stoneforze.pagepilot.data.model.Highlight
import com.stoneforze.pagepilot.data.model.PdfDocument
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Insert
    suspend fun insertPdf(pdf: PdfDocument)

    @Query("SELECT * FROM pdfs ORDER BY addedAt DESC")
    fun getAllPdfs(): Flow<List<PdfDocument>>

    @Insert
    suspend fun insertHighlight(highlight: Highlight)

    @Query("SELECT * FROM highlights WHERE pdfId = :pdfId")
    fun getHighlightsForPdf(pdfId: Int): Flow<List<Highlight>>

    @Insert
    suspend fun insertFlashcard(flashcard: Flashcard)

    @Query("SELECT * FROM flashcards WHERE pdfId = :pdfId")
    fun getFlashcardsForPdf(pdfId: Int): Flow<List<Flashcard>>
}
