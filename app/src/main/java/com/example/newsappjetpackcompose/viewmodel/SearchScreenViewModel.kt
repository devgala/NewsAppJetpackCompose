package com.example.newsappjetpackcompose.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappjetpackcompose.NewsResponse
import com.example.newsappjetpackcompose.pagination.DefaultPaginator
import com.example.newsappjetpackcompose.pagination.paginatonRepository
import com.example.newsappjetpackcompose.repository.SearchRepository
import kotlinx.coroutines.launch

class SearchScreenViewModel: ViewModel() {

    private val repository = SearchRepository()
    private val _searchedNews = MutableLiveData<NewsResponse>()
    val searchedNews: LiveData<NewsResponse> = _searchedNews
    var searchedNewsPage = 1

    var searchQuery by mutableStateOf("")

    fun fetchSearchedNews() {
        viewModelScope.launch {
            try{
                val searchResponse = repository.getSearchedNews(searchQuery, searchedNewsPage)
                _searchedNews.value=searchResponse
            } catch (e: Exception){
                Log.d("Api Call", e.stackTrace.toString())
            }
        }
    }

    val Prepository = paginatonRepository()
    var screenState by  mutableStateOf(NewsScreenState())
    private val paginator = DefaultPaginator(
        initialKey = screenState.page,
        onLoadUpdated = {
            screenState = screenState.copy(isLoading = it)
        },
        onError = {
            screenState = screenState.copy(error = it?.localizedMessage)
        },

        onRequest = { nextKey, category ->
            Prepository.getSearchResponse(page = nextKey, query = searchQuery)
        },
        onSuccess = {
                items,key->
            if (items != null) {
                screenState = screenState.copy(
                    items = screenState.items?.plus(items) ,
                    page =  key,
                    endReached = items.isEmpty()

                )
            }
        },
        getNextKey = {
            screenState.page+1
        }

    )

    fun getNewsTest(){
        viewModelScope.launch {
            paginator.loadNextArticles("")
        }
    }
}

data class SearchScreenState(
    val isLoading: Boolean = false,
    val items: List<com.example.newsappjetpackcompose.Article>? = emptyList(),
    val error: String? = null,
    val page: Int = 1,
    val endReached:Boolean = false
);