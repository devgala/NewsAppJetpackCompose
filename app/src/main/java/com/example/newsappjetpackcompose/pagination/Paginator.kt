package com.example.newsappjetpackcompose.pagination

interface Paginator<Item> {
    suspend fun  loadNextArticles(category: String)
    fun reset()
}