package com.stoneforze.pagepilot.ui.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stoneforze.pagepilot.data.model.PdfDocument
import com.stoneforze.pagepilot.databinding.ItemPdfBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PdfAdapter(private val onItemClick: (PdfDocument) -> Unit) :
    ListAdapter<PdfDocument, PdfAdapter.PdfViewHolder>(PdfComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfViewHolder {
        val binding = ItemPdfBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PdfViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PdfViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class PdfViewHolder(private val binding: ItemPdfBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }

        fun bind(pdf: PdfDocument) {
            binding.tvPdfTitle.text = pdf.title

            val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            val dateString = formatter.format(Date(pdf.addedAt))
            binding.tvPdfDetails.text = "Added $dateString • Page ${pdf.lastReadPage}"
        }
    }

    class PdfComparator : DiffUtil.ItemCallback<PdfDocument>() {
        override fun areItemsTheSame(oldItem: PdfDocument, newItem: PdfDocument): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PdfDocument, newItem: PdfDocument): Boolean {
            return oldItem == newItem
        }
    }
}
