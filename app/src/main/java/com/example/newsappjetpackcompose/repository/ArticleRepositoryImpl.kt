package com.example.newsappjetpackcompose.repository

import androidx.lifecycle.LiveData
import com.example.newsappjetpackcompose.db.ArticleDao
import com.example.newsappjetpackcompose.model.Article

class ArticleRepositoryImpl(
    private  val articleDao: ArticleDao
):ArticleRepository {
    override fun getAllArticles(): LiveData<List<Article>> {
        return articleDao.getAllArticles()
    }

    override suspend fun insertArticle(article: Article) {
        articleDao.insert(article)
    }

    override suspend fun deleteArticle(article: Article) {
       articleDao.delete(article.id)
    }
}