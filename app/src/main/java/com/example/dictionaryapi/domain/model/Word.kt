package com.example.dictionaryapi.domain.model

import com.google.gson.annotations.SerializedName

data class Word(
    @SerializedName("word")
    var word: String,
    @SerializedName("phonetic")
    var phonetic: String,
    @SerializedName("phonetics")
    var phonetics: List<Phonetics>,
    @SerializedName("meanings")
    var meanings: List<MeaningsList>
)
