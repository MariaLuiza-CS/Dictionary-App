package com.example.dictionaryapi.domain.model

import com.google.gson.annotations.SerializedName

data class MeaningsList(
    @SerializedName("definitions")
    var definitions: List<Meanings>
)
