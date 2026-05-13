package com.stoneforze.pagepilot.data

import com.stoneforze.pagepilot.data.model.PdfDocument
import kotlinx.coroutines.flow.Flow

class PdfRepository(private val appDao: AppDao) {

    val allPdfs: Flow<List<PdfDocument>> = appDao.getAllPdfs()

    suspend fun insertPdf(pdf: PdfDocument) {
        appDao.insertPdf(pdf)
    }
}
