package com.example.newsappjetpackcompose.repository

import com.example.newsappjetpackcompose.api.RetrofitInstance

class SearchRepository {
    suspend fun getSearchedNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.getSearchedNews(searchQuery, pageNumber)
}