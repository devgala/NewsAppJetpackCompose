package com.example.newsappjetpackcompose.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsappjetpackcompose.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {
    @Query("SELECT * FROM articleTable")
    fun getAllArticles(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("DELETE FROM articleTable WHERE id=:id")
    suspend fun delete(id:Int)

    @Query("SELECT URL FROM ARTICLETABLE WHERE URL= :url")
    suspend fun isPresent(url : String):String

    @Query("SELECT ID FROM ARTICLETABLE WHERE URL = :url")
    suspend fun idFromUrl(url:String):Int

}