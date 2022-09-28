package com.ovia.composevsxml

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ovia.composevsxml.models.SearchResults
import com.ovia.composevsxml.network.DataState
import com.ovia.composevsxml.network.NetworkingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: NetworkingRepository) : ViewModel() {

    private val _state = MutableStateFlow<DataState>(DataState.Loading).apply {
        this.value = DataState.Success(DataState.SuccessType.SEARCH_START)
    }
    internal val state: StateFlow<DataState> = _state

    private val _data = MutableLiveData<SearchResults>().apply {
        this.value = SearchResults()
    }
    internal val data: LiveData<SearchResults> = _data

    fun performQuery(query: String) {
        _state.value = DataState.Loading
        viewModelScope.launch {
            safeNetworkCall {
                val results = repository.performSearch(query)
                _state.value = if (results.totalResults <= 0) {
                    DataState.Success(DataState.SuccessType.SEARCH_NO_RESULTS)
                } else {
                    _data.value = results
                    DataState.Success(DataState.SuccessType.NOT_EMPTY)
                }
            }
        }
    }

    private suspend fun safeNetworkCall(call: suspend () -> Unit) {
        return try {
            call()
        } catch (e: Exception) {
            _state.value = DataState.Error
            e.printStackTrace()
        }
    }

    class SearchViewModelFactory(private val repository: NetworkingRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST") // isAssignableFrom proves the cast will succeed
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                return SearchViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
