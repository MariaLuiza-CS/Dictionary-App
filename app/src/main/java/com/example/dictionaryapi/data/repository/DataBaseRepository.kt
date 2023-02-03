package com.example.dictionaryapi.data.repository

import com.example.dictionaryapi.domain.model.WordEntity

interface DataBaseRepository {
    suspend fun getAllWords(): List<WordEntity>
    suspend fun getFavoriteWords(): List<String>
    suspend fun getWord(word: String): WordEntity
    suspend fun insertWord(word: WordEntity)
    suspend fun setFavorite(favorite: String, word: String)
}