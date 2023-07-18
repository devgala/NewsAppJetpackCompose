package com.example.newsappjetpackcompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappjetpackcompose.pagination.DefaultPaginator
import com.example.newsappjetpackcompose.pagination.paginatonRepository
import kotlinx.coroutines.launch

class SearchScreenViewModel : ViewModel() {
    var searchQuery by mutableStateOf("")
    val Prepository = paginatonRepository()
    var screenState by mutableStateOf(SearchScreenState())
     val paginator = DefaultPaginator(
        initialKey = screenState.page,
        onLoadUpdated = {
            screenState = screenState.copy(isLoading = it)
        },
        onError = {
            screenState = screenState.copy(error = it?.localizedMessage)
        },


        onRequest = { (nextKey, category) ->
            Prepository.getSearchResponse(page = nextKey, query = searchQuery)
        },
        onSuccess = { items, key ->
            if (items != null) {
                screenState = screenState.copy(
                    items = screenState.items?.plus(items),
                    page = key,
                    endReached = items.isEmpty()

                )
            }
        },
        getNextKey = {
            screenState.page + 1
        }

    )
    fun resetList(){
        screenState  = screenState.copy(items = emptyList())
    }

    fun getNewsTest(language:String="en") {

        viewModelScope.launch {
            Prepository.language=language
            paginator.loadNextArticles("")
        }
    }
}

data class SearchScreenState(
    val isLoading: Boolean = false,
    val items: List<com.example.newsappjetpackcompose.Article>? = emptyList(),
    val error: String? = null,
    val page: Int = 1,
    val endReached: Boolean = false
);