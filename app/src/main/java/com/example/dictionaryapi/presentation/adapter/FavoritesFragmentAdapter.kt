package com.example.dictionaryapi.presentation.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapi.databinding.ItemWordBinding

class FavoritesFragmentAdapter(val context: Context) :
    RecyclerView.Adapter<FavoritesFragmentAdapter.FavoritesFragmentViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null
    private lateinit var binding: ItemWordBinding

    class FavoritesFragmentViewHolder(itemView: ItemWordBinding) :
        RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesFragmentViewHolder {
        binding = ItemWordBinding.inflate(LayoutInflater.from(context), parent, false)
        return FavoritesFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesFragmentViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        var randomNumber = (0..2).random()
        var colorsArray = listOf("#FBC797", "#7EDEFB", "#FE8C2A")

        binding.textViewWord.text = differ.currentList[position]
        binding.cardViewWord.setOnClickListener {
            onItemClick?.invoke(differ.currentList[position])
        }
        binding.cardViewWord.setCardBackgroundColor(Color.parseColor(colorsArray[randomNumber]))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}