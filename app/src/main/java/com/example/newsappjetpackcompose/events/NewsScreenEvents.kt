package com.example.newsappjetpackcompose.events

import com.example.newsappjetpackcompose.Article

sealed class NewsScreenEvents{
    data class  onSaveClick(val article: Article):NewsScreenEvents()
    object onArticleClick:NewsScreenEvents()
}
