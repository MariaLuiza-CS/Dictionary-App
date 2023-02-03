package com.example.dictionaryapi.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "word",
    indices = [Index(value = ["word"], unique = true)]
)
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val wordId: Int,
    @ColumnInfo(name = "word")
    var word: String?,
    @ColumnInfo(name = "phonetic")
    var phonetic: String?,
    @ColumnInfo(name = "phoneticText")
    var phoneticText: String?,
    @ColumnInfo(name = "phoneticAudio")
    var phoneticAudio: String?,
    @ColumnInfo(name = "definitionOne")
    var definitionOne: String?,
    @ColumnInfo(name = "definitionTwo")
    var definitionTwo: String?,
    @ColumnInfo(name = "favorite")
    var favorite: String?
)
