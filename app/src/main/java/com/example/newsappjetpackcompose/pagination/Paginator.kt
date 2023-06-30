package com.example.newsappjetpackcompose.pagination

interface Paginator<Item> {
    suspend fun  loadNextArticles()
    fun reset()
}