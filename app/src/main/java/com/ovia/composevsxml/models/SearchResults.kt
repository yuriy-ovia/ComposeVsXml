package com.ovia.composevsxml.models

import com.google.gson.annotations.SerializedName
import com.ovia.composevsxml.models.Result

// Suppress unused warnings because these fields are actually used by GSON parsing
@Suppress("unused")
data class SearchResults(
    @SerializedName("Search")
    var searchResults: List<Result>? = null,

    var totalResults: Long = 0,

    @SerializedName("Response")
    var response: Boolean = false
)
