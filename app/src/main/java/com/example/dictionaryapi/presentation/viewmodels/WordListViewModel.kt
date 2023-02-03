package com.example.dictionaryapi.presentation.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapi.domain.model.Word
import com.example.dictionaryapi.domain.model.WordEntity
import com.example.dictionaryapi.data.repository.WordRepository
import com.example.dictionaryapi.domain.usecases.InsertWordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WordListViewModel(
    private val wordRepository: WordRepository,
    private val insertWordUseCase: InsertWordUseCase
) : ViewModel() {

    private val _wordResponse = MutableLiveData<Word>()

    val wordResponse: LiveData<Word>
        get() = _wordResponse

    fun getWord(context: Context, word: String) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                wordRepository.getWord(word).let {
                    if (it.isSuccessful) {
                        it.body()?.get(0)?.let { word ->

                            var phoneticsText: String? = null
                            var phoneticsAudio: String? = null
                            var meaningsDefenitionOne: String? = null
                            var meaningsDefenitionTwo: String? = null

                            _wordResponse.postValue(word)

                            try {
                                if (word.meanings[0]!= null && word.meanings[0].definitions[0] != null) {
                                    meaningsDefenitionOne =
                                        word.meanings[0].definitions[0].definitions
                                }
                                if (word.meanings[0]!= null && word.meanings[0].definitions.size > 1 && word.meanings[0].definitions[1] != null) {
                                    meaningsDefenitionTwo =
                                word.meanings[0].definitions[1].definitions
                                }
                                if (!word.phonetics.isNullOrEmpty()) {
                                    if (!word.phonetics[0].text.isNullOrEmpty()) {
                                        phoneticsText = word.phonetics[0].text
                                    }
                                    if (!word.phonetics[0].audio.isNullOrEmpty()) {
                                        phoneticsAudio = word.phonetics[0].audio
                                    }
                                }

                                insertWord(
                                    WordEntity(
                                        wordId = 0,
                                        word = word.word,
                                        phonetic = word.phonetic,
                                        phoneticText = phoneticsText,
                                        phoneticAudio = phoneticsAudio,
                                        definitionOne = meaningsDefenitionOne,
                                        definitionTwo = meaningsDefenitionTwo,
                                        favorite = "false"
                                    )
                                )

                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                "Sorry, i cant find that word!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Log.ERROR
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    private fun insertWord(word: WordEntity) =
        viewModelScope.launch {
            insertWordUseCase.invoke(word)
        }
}
