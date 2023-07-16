package com.example.newsappjetpackcompose.api

import com.example.newsappjetpackcompose.NewsResponse
import com.example.newsappjetpackcompose.util.Constants.Companion.API_KEY
import com.example.newsappjetpackcompose.util.Language
import retrofit2.http.GET

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @retrofit2.http.Query("country")
        countryCode: String = "in",
        @retrofit2.http.Query("page")
        pageNumber: Int = 1,
        @retrofit2.http.Query("apiKey")
        apiKey: String = API_KEY,
        @retrofit2.http.Query("pageSize")
        pageSize:Int = 10,
        @retrofit2.http.Query("language")
        language: String = "en"
    ): NewsResponse

    @GET("v2/everything")
    suspend fun getSearchedNews(
        @retrofit2.http.Query("q")
        searchQuery: String? = null,
        @retrofit2.http.Query("page")
        pageNumber: Int = 1,
        @retrofit2.http.Query("apiKey")
        apiKey: String = API_KEY,
        @retrofit2.http.Query("pageSize")
        pageSize:Int = 10,
        @retrofit2.http.Query("language")
        language: String = "en"
    ): NewsResponse
}