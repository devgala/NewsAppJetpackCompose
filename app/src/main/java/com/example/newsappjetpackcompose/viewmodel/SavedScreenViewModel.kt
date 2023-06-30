package com.example.newsappjetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappjetpackcompose.events.SavedScreenEvents
import com.example.newsappjetpackcompose.events.UiEventsSavedScreen
import com.example.newsappjetpackcompose.model.Article
import com.example.newsappjetpackcompose.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedScreenViewModel @Inject constructor(val repository:ArticleRepository) : ViewModel() {
        val articles = repository.getAllArticles()
    var deletedArticle:Article? = null
    var savedArticle:Article? = null
    private val _uiEvent = Channel<UiEventsSavedScreen>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun onEvent(event:SavedScreenEvents){
        when(event){
            is SavedScreenEvents.onClickArticle->{
                //open web view
            }
            is SavedScreenEvents.onClickDelete->{

                viewModelScope.launch {
                    if(event.article.id==0){
                        event.article.id = repository.idFromUrl(event.article.url)
                    }
                    deletedArticle = event.article
                    repository.deleteArticle(event.article)
                    sendUIEvent(UiEventsSavedScreen.showSnackBar("Article Deleted",action = "UNDO"))
                }
            }
            is SavedScreenEvents.onClickUndoDelete->{
                deletedArticle?.let {
                    viewModelScope.launch {
                        repository.insertArticle(it)
                    }
                }
            }
            is SavedScreenEvents.onClickAdd->{
                viewModelScope.launch {
                    //savedArticle = event.article
                    event.article?.let {
                        repository.insertArticle(it)
                    }
                    sendUIEvent(UiEventsSavedScreen.showSnackBar("Article Saved", action = "Undo"))
                }
            }
            is SavedScreenEvents.onNotClickUndoAdd->{
                viewModelScope.launch{

                  // savedArticle?.let { repository.insertArticle(it)}
                }
            }
        }
    }
    fun sendUIEvent(event: UiEventsSavedScreen){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}