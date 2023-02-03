package com.example.dictionaryapi.domain.usecases

import com.example.dictionaryapi.data.repository.DataBaseRepository
import com.example.dictionaryapi.domain.model.WordEntity

class InsertWordUseCase(
    private val dataBaseRepository: DataBaseRepository
) {
    suspend operator fun invoke(word: WordEntity) =
        dataBaseRepository.insertWord(word)
}