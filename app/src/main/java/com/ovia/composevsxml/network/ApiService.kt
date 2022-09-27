package com.ovia.composevsxml.network

import retrofit2.http.GET
import com.ovia.composevsxml.models.SearchResults
import retrofit2.http.Query

interface ApiService {
    @GET("/?apikey=ee60b1cc")
    suspend fun performSearch(
        @Query("s") searchTerm: String?,
        @Query("page") pageNum: Int
    ): SearchResults
}
