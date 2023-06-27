package com.example.newsappjetpackcompose.viewmodel

import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappjetpackcompose.NewsResponse
import com.example.newsappjetpackcompose.events.NewsScreenEvents
import com.example.newsappjetpackcompose.model.Article
import com.example.newsappjetpackcompose.repository.ArticleRepository
import com.example.newsappjetpackcompose.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


class NewsViewModel  : ViewModel() {
    private val repository = NewsRepository()
    private val _breakingNews = MutableLiveData<NewsResponse>()
    val breakingNews: LiveData<NewsResponse> = _breakingNews
    var breakingNewsPage = 1


    fun fetchBreakingNews(){
        viewModelScope.launch {
            try{
                val newsResponse = repository.getBreakingNews("in", breakingNewsPage)
                Log.d("ViewModelTest", newsResponse.totalResults.toString())
                _breakingNews.value=newsResponse
            } catch (e: Exception){
                Log.d("Api Call", e.stackTrace.toString())
            }
        }
    }





}