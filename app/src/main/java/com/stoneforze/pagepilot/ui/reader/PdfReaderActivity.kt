package com.stoneforze.pagepilot.ui.reader

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.stoneforze.pagepilot.databinding.ActivityPdfReaderBinding

class PdfReaderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPdfReaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfReaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pdfId = intent.getIntExtra(EXTRA_PDF_ID, -1)
        val pdfTitle = intent.getStringExtra(EXTRA_PDF_TITLE) ?: "Document"
        val pdfPath = intent.getStringExtra(EXTRA_PDF_PATH)

        binding.toolbarReader.title = pdfTitle
        binding.toolbarReader.setNavigationOnClickListener { finish() }

        Log.d("PdfReader", "Opening PDF ID: $pdfId, Path: $pdfPath")
    }

    companion object {
        const val EXTRA_PDF_ID = "extra_pdf_id"
        const val EXTRA_PDF_TITLE = "extra_pdf_title"
        const val EXTRA_PDF_PATH = "extra_pdf_path"
    }
}
