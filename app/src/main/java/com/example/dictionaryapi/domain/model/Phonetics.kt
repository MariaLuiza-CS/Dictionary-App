package com.example.dictionaryapi.domain.model

import com.google.gson.annotations.SerializedName

data class Phonetics(
    @SerializedName("text")
    var text: String?,
    @SerializedName("audio")
    var audio: String?
)
