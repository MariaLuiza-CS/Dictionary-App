package com.example.dictionaryapi.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionaryapi.R
import com.example.dictionaryapi.databinding.FragmentFavoritesBinding
import com.example.dictionaryapi.presentation.adapter.FavoritesFragmentAdapter
import com.example.dictionaryapi.presentation.viewmodels.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: FavoritesFragmentAdapter
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initObserve()
        initView(view)
    }

    private fun initView(view: View) {

        adapter = FavoritesFragmentAdapter(view.context)
        binding.recyclerViewWordList.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerViewWordList.adapter = adapter

        adapter.onItemClick = {
            val bundle = Bundle()
            bundle.putString("word", it)
            Navigation.findNavController(view)
                .navigate(R.id.action_favoritesFragment_to_wordFragment, bundle)
        }
    }

    private fun initViewModel() {
        viewModel.getFavoriteWords()
    }

    private fun initObserve() {
        viewModel.word.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                adapter.differ.submitList(it)
                binding.imageViewFavorite.visibility = View.GONE
                binding.textViewOps.visibility = View.GONE
                binding.recyclerViewWordList.visibility = View.VISIBLE
            } else {
                binding.imageViewFavorite.visibility = View.VISIBLE
                binding.textViewOps.visibility = View.VISIBLE
                binding.recyclerViewWordList.visibility = View.GONE
            }
        }
    }
}
