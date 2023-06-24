package com.example.newsappjetpackcompose.di

import android.app.Application
import androidx.room.Room
import com.example.newsappjetpackcompose.db.ArticleDatabase
import com.example.newsappjetpackcompose.repository.ArticleRepository
import com.example.newsappjetpackcompose.repository.ArticleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideArticleDatabase(app:Application):ArticleDatabase{
        return Room.databaseBuilder(
            app,
            ArticleDatabase::class.java,
            "newsDB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideArticleRepository(db:ArticleDatabase):ArticleRepository{
        return ArticleRepositoryImpl(db.articleDAO)
    }

}