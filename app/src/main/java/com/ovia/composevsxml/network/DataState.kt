package com.ovia.composevsxml.network

internal sealed class DataState {

    object Loading : DataState()
    object Error : DataState()

    class Success(val type: SuccessType) : DataState()

    enum class SuccessType {
        NOT_EMPTY,
        SEARCH_START,
        SEARCH_NO_RESULTS,
    }
}
