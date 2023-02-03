package com.example.dictionaryapi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dictionaryapi.domain.model.WordEntity

@Dao
interface WordDao {

    @Query("SELECT * FROM word")
    fun getAllWords(): List<WordEntity>

    @Query("SELECT * FROM word WHERE word= :word")
    fun getWord(word: String): WordEntity

    @Query("SELECT word FROM word WHERE favorite= 'true'")
    fun getFavoriteWords(): List<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWord(word: WordEntity)

    @Query("UPDATE word SET favorite=:favorite WHERE  word= :word")
    fun setFavorite(favorite: String, word: String)
}
