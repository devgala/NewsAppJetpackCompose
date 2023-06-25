package com.example.newsappjetpackcompose

data class NewsResponse(
    val articles: List<Article> = emptyList(),
    val status: String = "",
    val totalResults: Int = 0
)