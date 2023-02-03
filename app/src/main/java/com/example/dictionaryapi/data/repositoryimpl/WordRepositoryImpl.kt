package com.example.dictionaryapi.data.repositoryimpl

import com.example.dictionaryapi.domain.model.Word
import com.example.dictionaryapi.data.remote.WordApi
import com.example.dictionaryapi.data.repository.WordRepository
import retrofit2.Response

class WordRepositoryImpl (
    private val wordApi: WordApi
) : WordRepository {

    override suspend fun getWord(word: String): Response<List<Word>> {
        return wordApi.getWord(word)
    }

}
