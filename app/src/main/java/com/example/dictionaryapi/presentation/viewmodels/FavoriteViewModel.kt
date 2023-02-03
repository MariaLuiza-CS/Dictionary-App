package com.example.dictionaryapi.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapi.domain.usecases.GetFavoriteWordsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getFavoriteWords: GetFavoriteWordsUseCase
):ViewModel() {
    private val _word = MutableLiveData<List<String>>()

    val word: LiveData<List<String>>
        get() = _word

    fun getFavoriteWords() =
        viewModelScope.launch(Dispatchers.IO) {
            _word.postValue(getFavoriteWords.invoke())
        }
}