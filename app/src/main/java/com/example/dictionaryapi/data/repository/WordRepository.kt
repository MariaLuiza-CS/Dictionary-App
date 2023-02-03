package com.example.dictionaryapi.data.repository

import com.example.dictionaryapi.domain.model.Word
import retrofit2.Response

interface WordRepository {
    suspend fun getWord(word: String): Response<List<Word>>
}