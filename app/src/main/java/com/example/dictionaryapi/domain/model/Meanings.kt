package com.example.dictionaryapi.domain.model

import com.google.gson.annotations.SerializedName

data class Meanings(
    @SerializedName("definition")
    var definitions: String
)
