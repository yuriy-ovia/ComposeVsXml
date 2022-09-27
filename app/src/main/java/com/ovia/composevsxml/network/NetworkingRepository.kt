package com.ovia.composevsxml.network

import com.ovia.composevsxml.models.SearchResults

class NetworkingRepository(private val apiService: ApiService) {

    suspend fun performSearch(query: String): SearchResults {
        return apiService.performSearch(query, 1).apply {
            searchResults = searchResults?.shuffled()
        }
    }

}
