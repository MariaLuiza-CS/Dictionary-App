package com.example.dictionaryapi.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionaryapi.presentation.adapter.HistoryFragmentAdapter
import com.example.dictionaryapi.R
import com.example.dictionaryapi.databinding.FragmentHistoryBinding
import com.example.dictionaryapi.presentation.viewmodels.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {
    private lateinit var adapter: HistoryFragmentAdapter
    private val viewModel: HistoryViewModel by viewModel()
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initObserve()
        initView(view)
    }

    private fun initView(view: View) {
        adapter = HistoryFragmentAdapter(view.context)
        binding.recyclerViewWordList.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerViewWordList.adapter = adapter

        adapter.onItemClick = {
            val bundle = Bundle()
            bundle.putString("word", it.word)
            Navigation.findNavController(view)
                .navigate(R.id.action_historyFragment_to_wordFragment, bundle)
        }
    }

    private fun initViewModel() {
        viewModel.getAllWords()
    }

    private fun initObserve() {
        viewModel.word.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                adapter.differ.submitList(it)
                binding.imageViewHistory.visibility = View.GONE
                binding.textViewOps.visibility = View.GONE
                binding.recyclerViewWordList.visibility = View.VISIBLE
            }else{
                binding.imageViewHistory.visibility = View.VISIBLE
                binding.textViewOps.visibility = View.VISIBLE
                binding.recyclerViewWordList.visibility = View.GONE
            }
        }
    }

}