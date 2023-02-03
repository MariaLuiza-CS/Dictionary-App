package com.example.dictionaryapi.domain.usecases

import com.example.dictionaryapi.data.repository.DataBaseRepository
import com.example.dictionaryapi.domain.model.WordEntity

class GetAllWordsUseCase(
    private val dataBaseRepository: DataBaseRepository
) {
    suspend operator fun invoke(): List<WordEntity> =
        dataBaseRepository.getAllWords()
}