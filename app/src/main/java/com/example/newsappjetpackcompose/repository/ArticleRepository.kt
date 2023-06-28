package com.example.newsappjetpackcompose.repository

import androidx.lifecycle.LiveData
import com.example.newsappjetpackcompose.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getAllArticles() : Flow<List<Article>>
    suspend fun insertArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    suspend fun isPresent(articleURL: String):Boolean
    suspend fun idFromUrl(articleURL: String):Int
}
