package com.example.dictionaryapi.domain.usecases

import com.example.dictionaryapi.data.repository.DataBaseRepository

class GetFavoriteWordsUseCase (
    private val dataBaseRepository: DataBaseRepository
) {
    suspend operator fun invoke(): List<String> =
        dataBaseRepository.getFavoriteWords()
}
