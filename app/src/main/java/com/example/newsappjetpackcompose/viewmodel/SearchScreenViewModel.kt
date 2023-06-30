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
}