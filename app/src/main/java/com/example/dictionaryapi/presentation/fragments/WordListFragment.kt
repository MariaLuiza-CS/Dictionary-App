package com.example.dictionaryapi.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dictionaryapi.R
import com.example.dictionaryapi.presentation.adapter.WordListFragmentAdapter
import com.example.dictionaryapi.databinding.FragmentWordListBinding
import com.example.dictionaryapi.presentation.viewmodels.WordListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class WordListFragment : Fragment() {
    private lateinit var adapter: WordListFragmentAdapter
    private val viewModel: WordListViewModel by viewModel()
    private lateinit var binding: FragmentWordListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWordListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        initObserve(view)
    }

    private fun initView(view: View) {
        val fileName = "wordlist.txt"
        val townList = arrayListOf<String>()
        view.context.assets.open(fileName).bufferedReader().forEachLine {
            townList.add(it)
        }

        adapter = WordListFragmentAdapter(view.context)
        binding.recyclerViewWordList.layoutManager = GridLayoutManager(view.context, 2)
        binding.recyclerViewWordList.adapter = adapter
        adapter.differ.submitList(townList)

        adapter.onItemClick = {
            viewModel.getWord(view.context, it)
        }
    }

    private fun initObserve(view: View) {
        viewModel.wordResponse.observe(viewLifecycleOwner) {
            val bundle = Bundle()
            bundle.putString("word", it.word)
            Navigation.findNavController(view)
                .navigate(R.id.action_wordListFragment_to_wordFragment, bundle)
        }
    }
}
