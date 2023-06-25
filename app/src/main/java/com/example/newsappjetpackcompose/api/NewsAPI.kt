package com.example.newsappjetpackcompose.api

import androidx.room.Query
import com.example.newsappjetpackcompose.NewsResponse
import com.example.newsappjetpackcompose.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @retrofit2.http.Query("country")
        countryCode: String = "in",
        @retrofit2.http.Query("page")
        pageNumber: Int = 1,
        @retrofit2.http.Query("apiKey")
        apiKey: String = API_KEY
    ): NewsResponse

}