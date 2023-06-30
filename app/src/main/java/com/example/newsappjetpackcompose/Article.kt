package com.example.newsappjetpackcompose

data class Article(
    val author: String,
    val content: String,
    val description: String = "this is desc",
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)