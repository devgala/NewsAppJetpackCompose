package com.example.newsappjetpackcompose.repository

import androidx.lifecycle.LiveData
import com.example.newsappjetpackcompose.db.ArticleDao
import com.example.newsappjetpackcompose.model.Article
import kotlinx.coroutines.flow.Flow

class ArticleRepositoryImpl(
    private  val articleDao: ArticleDao
):ArticleRepository {
    override fun getAllArticles(): Flow<List<Article>> {
        return articleDao.getAllArticles()
    }

    override suspend fun insertArticle(article: Article) {

        articleDao.insert(article)
    }

    override suspend fun deleteArticle(article: Article) {
       articleDao.delete(article.id)
    }

    override suspend fun isPresent(articleURL: String) :Boolean{
        articleDao.isPresent(articleURL).let {
            url ->
            return url == articleURL
        }
    }

    override suspend fun idFromUrl(articleURL: String) :Int{
        return  articleDao.idFromUrl(articleURL)

    }
}