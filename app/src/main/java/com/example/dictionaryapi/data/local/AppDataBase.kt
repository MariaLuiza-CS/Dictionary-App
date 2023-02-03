package com.example.dictionaryapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dictionaryapi.data.dao.WordDao
import com.example.dictionaryapi.domain.model.WordEntity

@Database(
    entities = [WordEntity::class],
    version = 8,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract val wordDao: WordDao
}
