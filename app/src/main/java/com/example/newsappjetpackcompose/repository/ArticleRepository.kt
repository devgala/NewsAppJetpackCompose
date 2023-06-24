package com.example.newsappjetpackcompose.repository

import androidx.lifecycle.LiveData
import com.example.newsappjetpackcompose.model.Article

interface ArticleRepository {
    fun getAllArticles() : LiveData<List<Article>>
    suspend fun insertArticle(article: Article)
    suspend fun deleteArticle(article: Article)
}
