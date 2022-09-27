package com.ovia.composevsxml.models

import com.google.gson.annotations.SerializedName

// Suppress unused warnings because these fields are actually used by GSON parsing
@Suppress("unused")
data class Result(
    @SerializedName("Title")
    var title: String? = null,

    @SerializedName("Year")
    var year: String? = null,

    var imdbID: String? = null,

    @SerializedName("Type")
    var type: String? = null,

    @SerializedName("Poster")
    var poster: String? = null
)
