package com.example.newsappjetpackcompose.repository

import com.example.newsappjetpackcompose.api.RetrofitInstance
import com.example.newsappjetpackcompose.db.ArticleDatabase

class NewsRepository {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int, category: String,language:String="en") =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber, category = category, language = language)

    suspend fun getSearchedNews(searchQuery: String, pageNumber: Int,language:String="en") =
        RetrofitInstance.api.getSearchedNews(searchQuery, pageNumber, language = language)
}