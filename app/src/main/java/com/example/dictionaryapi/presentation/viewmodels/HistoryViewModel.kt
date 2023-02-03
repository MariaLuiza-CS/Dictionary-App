package com.example.dictionaryapi.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapi.domain.model.WordEntity
import com.example.dictionaryapi.domain.usecases.GetAllWordsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val getAllWordsUseCase: GetAllWordsUseCase
) : ViewModel() {
    private val _word = MutableLiveData<List<WordEntity>>()

    val word: LiveData<List<WordEntity>>
        get() = _word

    fun getAllWords() = viewModelScope.launch(Dispatchers.IO) {
        _word.postValue(getAllWordsUseCase.invoke())
    }
}