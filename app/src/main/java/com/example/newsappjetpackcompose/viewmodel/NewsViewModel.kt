package com.example.newsappjetpackcompose.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappjetpackcompose.NewsResponse
import com.example.newsappjetpackcompose.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()
    private val _breakingNews = MutableLiveData<NewsResponse>()
    val breakingNews: LiveData<NewsResponse> = _breakingNews
    var breakingNewsPage = 1


    fun fetchBreakingNews(){
        viewModelScope.launch {
            try{
                val newsResponse = repository.getBreakingNews("in", breakingNewsPage)
                _breakingNews.value=newsResponse
            } catch (e: Exception){
                Log.d("Api Call", e.stackTrace.toString())
            }
        }
    }



}