package com.example.dictionaryapi.data.remote

import com.example.dictionaryapi.domain.model.Word
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WordApi {
    @GET("entries/en/{word}")
    suspend fun getWord(@Path("word") word: String): Response<List<Word>>
}