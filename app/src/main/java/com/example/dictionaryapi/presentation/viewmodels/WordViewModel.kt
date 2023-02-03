package com.example.dictionaryapi.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapi.domain.model.WordEntity
import com.example.dictionaryapi.domain.usecases.GetWordUseCase
import com.example.dictionaryapi.domain.usecases.SetFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WordViewModel(
    private val getWordUseCase: GetWordUseCase,
    private val favoriteUseCase: SetFavoriteUseCase
) : ViewModel() {
    private val _word = MutableLiveData<WordEntity>()

    val word: LiveData<WordEntity>
        get() = _word

    fun getWord(word: String) =
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _word.value = getWordUseCase.invoke(word)
            }
        }

    fun setFavorite(favorite: String, word: String) =
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                favoriteUseCase.invoke(favorite, word)
            }
        }
}
