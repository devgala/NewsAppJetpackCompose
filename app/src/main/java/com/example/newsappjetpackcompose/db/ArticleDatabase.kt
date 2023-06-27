package com.example.newsappjetpackcompose.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsappjetpackcompose.model.Article

@Database(entities = [Article::class], version = 2, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {
    abstract val articleDAO: ArticleDao

}