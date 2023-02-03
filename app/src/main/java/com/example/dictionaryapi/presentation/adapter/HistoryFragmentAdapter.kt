package com.example.dictionaryapi.presentation.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapi.domain.model.WordEntity
import com.example.dictionaryapi.databinding.ItemHistoryBinding

class HistoryFragmentAdapter(val context: Context) :
    RecyclerView.Adapter<HistoryFragmentAdapter.HistoryFragmentViewHolder>() {

    var onItemClick: ((WordEntity) -> Unit)? = null
    private lateinit var binding: ItemHistoryBinding

    class HistoryFragmentViewHolder(itemView: ItemHistoryBinding) :
        RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryFragmentViewHolder {
        binding = ItemHistoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return HistoryFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryFragmentViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        var randomNumber = (0..2).random()
        var colorsArray = listOf("#FBC797", "#7EDEFB", "#FE8C2A")

        binding.textViewWord.text = differ.currentList[position].word
        binding.cardViewWord.setOnClickListener {
            onItemClick?.invoke(differ.currentList[position])
        }

        binding.cardViewWord.setCardBackgroundColor(Color.parseColor("${colorsArray[randomNumber]}"))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<WordEntity>() {
        override fun areItemsTheSame(oldItem: WordEntity, newItem: WordEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WordEntity, newItem: WordEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}