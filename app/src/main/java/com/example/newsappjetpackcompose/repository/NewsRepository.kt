package com.example.newsappjetpackcompose.repository

import com.example.newsappjetpackcompose.api.RetrofitInstance
import com.example.newsappjetpackcompose.db.ArticleDatabase

class NewsRepository {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun getSearchedNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.getSearchedNews(searchQuery, pageNumber)
}