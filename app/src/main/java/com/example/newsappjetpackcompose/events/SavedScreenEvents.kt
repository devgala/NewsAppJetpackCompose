package com.example.newsappjetpackcompose.events

import com.example.newsappjetpackcompose.model.Article

sealed class SavedScreenEvents{
    data class onClickArticle(val article: Article):SavedScreenEvents()
    data class onClickDelete(val article: Article):SavedScreenEvents()
    object onClickUndoDelete:SavedScreenEvents()
    data class onClickAdd(val article: Article):SavedScreenEvents()

}
