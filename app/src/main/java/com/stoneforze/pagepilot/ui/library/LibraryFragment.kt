package com.stoneforze.pagepilot.ui.library

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.stoneforze.pagepilot.data.AppDatabase
import com.stoneforze.pagepilot.data.PdfRepository
import com.stoneforze.pagepilot.data.model.PdfDocument
import com.stoneforze.pagepilot.databinding.FragmentLibraryBinding
import kotlinx.coroutines.launch

class LibraryFragment : Fragment() {
    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!

    private val libraryViewModel: LibraryViewModel by viewModels {
        LibraryViewModelFactory(PdfRepository(AppDatabase.getDatabase(requireContext()).appDao()))
    }

    private lateinit var pdfAdapter: PdfAdapter

    private val openPdfLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
        uri?.let {
            // Need to persist permissions so we can access it later when reading
            requireContext().contentResolver.takePersistableUriPermission(
                it,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            importPdf(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pdfAdapter = PdfAdapter { clickedPdf ->
            Log.d("Library", "Clicked on PDF: ${clickedPdf.title}")
            val intent = Intent(requireContext(), com.stoneforze.pagepilot.ui.reader.PdfReaderActivity::class.java).apply {
                putExtra(com.stoneforze.pagepilot.ui.reader.PdfReaderActivity.EXTRA_PDF_ID, clickedPdf.id)
                putExtra(com.stoneforze.pagepilot.ui.reader.PdfReaderActivity.EXTRA_PDF_TITLE, clickedPdf.title)
                putExtra(com.stoneforze.pagepilot.ui.reader.PdfReaderActivity.EXTRA_PDF_PATH, clickedPdf.path)
            }
            startActivity(intent)
        }
        binding.rvPdfs.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pdfAdapter
        }

        binding.fabImportPdf.setOnClickListener {
            openPdfLauncher.launch(arrayOf("application/pdf"))
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                libraryViewModel.allPdfs.collect { pdfs ->
                    pdfAdapter.submitList(pdfs)
                }
            }
        }
    }

    private fun importPdf(uri: Uri) {
        var fileName = "Unknown Document"
        val cursor = requireContext().contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val displayNameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (displayNameIndex != -1) {
                    fileName = it.getString(displayNameIndex)
                }
            }
        }

        val newPdf = PdfDocument(
            title = fileName,
            path = uri.toString()
        )
        libraryViewModel.insertPdf(newPdf)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
