package com.example.dictionaryapi.presentation.fragments

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.dictionaryapi.R
import com.example.dictionaryapi.databinding.FragmentWordBinding
import com.example.dictionaryapi.domain.model.WordEntity
import com.example.dictionaryapi.presentation.viewmodels.WordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class WordFragment : Fragment() {

    private lateinit var binding: FragmentWordBinding
    private val viewModel: WordViewModel by viewModel()
    lateinit var mediaPlayer: MediaPlayer
    private lateinit var word: WordEntity
    private var lastImageResource: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initObserve()
    }

    private fun initObserve() {
        viewModel.word.observe(viewLifecycleOwner) {
            initView(it)
        }
    }

    private fun initViewModel() {
        viewModel.getWord(arguments?.getString("word").toString())
    }

    private fun initView(word: WordEntity?) {
        if (word != null) {
            this.word = word
        }

        binding.imageViewPlay.setOnClickListener {
            playAudio(word?.phoneticAudio)
        }

        if (word?.phoneticAudio != null) {
            binding.imageViewPlay.visibility = View.VISIBLE
            binding.progressBarWord.visibility = View.VISIBLE
        } else {
            binding.imageViewPlay.visibility = View.GONE
            binding.progressBarWord.visibility = View.GONE
        }

        binding.textViewHello.text = word?.word
        binding.textViewLearn.text = word?.phonetic

        if (word?.definitionOne != null) {
            binding.textViewMeaningOne.text = word.definitionOne
        }
        if (word?.definitionTwo != null) {
            binding.textViewMeaningTwo.text = word.definitionTwo
        }

        if (word?.favorite == "false") {
            binding.fabWordFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            lastImageResource = "bord"
        }
        if (word?.favorite == "true") {
            binding.fabWordFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            lastImageResource = "favorite"
        }

        binding.toolbarWord.setNavigationOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_wordFragment_to_wordListFragment)
        }

        binding.fabWordFavorite.setOnClickListener {
            if (lastImageResource == "favorite") {
                binding.fabWordFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                lastImageResource = "bord"
            } else {
                binding.fabWordFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                lastImageResource = "favorite"
            }
        }
    }

    private fun playAudio(audioUrl: String?) {
        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        mediaPlayer.setDataSource(audioUrl)
        mediaPlayer.prepare()

        initialiseSeekBar()

        mediaPlayer.start()

        binding.progressBarWord.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) mediaPlayer.seekTo(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    private fun initialiseSeekBar() {
        binding.progressBarWord.max = mediaPlayer.duration

        Handler(Looper.getMainLooper()).postDelayed({
            try {
                binding.progressBarWord.progress = mediaPlayer.currentPosition
            } catch (e: Exception) {
                binding.progressBarWord.progress = 0
            }
        }, 3000)
    }

    override fun onPause() {
        super.onPause()
        if (lastImageResource == "bord") {
            viewModel.setFavorite("false", word.word.toString())
        } else {
            viewModel.setFavorite("true", word.word.toString())
        }
    }
}