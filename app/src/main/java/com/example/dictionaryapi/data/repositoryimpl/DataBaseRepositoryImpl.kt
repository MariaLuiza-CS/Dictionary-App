package com.example.dictionaryapi.data.repositoryimpl

import com.example.dictionaryapi.data.dao.WordDao
import com.example.dictionaryapi.domain.model.WordEntity
import com.example.dictionaryapi.data.repository.DataBaseRepository

class DataBaseRepositoryImpl(private val wordDao: WordDao) : DataBaseRepository {
    override suspend fun getAllWords(): List<WordEntity> {
        return wordDao.getAllWords()
    }

    override suspend fun getFavoriteWords(): List<String> {
        return wordDao.getFavoriteWords()
    }

    override suspend fun getWord(word: String): WordEntity {
        return wordDao.getWord(word)
    }

    override suspend fun setFavorite(favorite: String, word: String) {
        wordDao.setFavorite(favorite, word)
    }

    override suspend fun insertWord(word: WordEntity) {
        wordDao.insertWord(word)
    }

}