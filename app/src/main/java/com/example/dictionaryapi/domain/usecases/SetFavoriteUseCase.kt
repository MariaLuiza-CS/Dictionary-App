package com.example.dictionaryapi.domain.usecases

import com.example.dictionaryapi.data.repository.DataBaseRepository

class SetFavoriteUseCase(
    private val dataBaseRepository: DataBaseRepository
) {
    suspend operator fun invoke(favorite: String, word: String) =
        dataBaseRepository.setFavorite(favorite, word)
}