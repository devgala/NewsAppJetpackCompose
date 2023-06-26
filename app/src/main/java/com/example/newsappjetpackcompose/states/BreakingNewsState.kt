package com.example.newsappjetpackcompose.states

import com.example.newsappjetpackcompose.Article
import com.example.newsappjetpackcompose.NewsResponse

data class BreakingNewsState (
    val newsList : List<Article>? = emptyList()
)