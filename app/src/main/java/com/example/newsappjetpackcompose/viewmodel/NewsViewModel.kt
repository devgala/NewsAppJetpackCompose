package com.example.newsappjetpackcompose.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappjetpackcompose.NewsResponse
import com.example.newsappjetpackcompose.events.NewsScreenEvents
import com.example.newsappjetpackcompose.model.Article
import com.example.newsappjetpackcompose.pagination.DefaultPaginator
import com.example.newsappjetpackcompose.pagination.paginatonRepository
import com.example.newsappjetpackcompose.repository.ArticleRepository
import com.example.newsappjetpackcompose.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


class NewsViewModel : ViewModel() {
    //    private val repository = NewsRepository()
//    private val _breakingNews = MutableLiveData<NewsResponse>()
//    val breakingNews: LiveData<NewsResponse> = _breakingNews
//    var breakingNewsPage = 1
//
//
//    fun fetchBreakingNews(){
//        viewModelScope.launch {
//            try{
//                val newsResponse = repository.getBreakingNews("in", breakingNewsPage)
//                _breakingNews.value=newsResponse
//            } catch (e: Exception){
//                Log.d("Api Call", e.stackTrace.toString())
//            }
//        }
//    }
    val repository = paginatonRepository()
    var screenState by  mutableStateOf(NewsScreenState())
    private val paginator = DefaultPaginator(
        initialKey = screenState.page,
        onLoadUpdated = {
            screenState = screenState.copy(isLoading = it)
        },
        onError = {
                  screenState = screenState.copy(error = it?.localizedMessage)
        },
        onRequest = { nextKey ->
            repository.getResponse(nextKey,10)
        },
        onSuccess = {
                items,key->
            if (items != null) {
                screenState = screenState.copy(
                    items = screenState.items?.plus(items) ,
                    page =  key,
                    endReached = items.isEmpty()

                )
            }
        },
        getNextKey = {
            screenState.page+1
        }

        )

     fun loadNextItems(){
        viewModelScope.launch {
            paginator.loadNextArticles()
        }
    }
    init {
        loadNextItems()
    }
}

data class NewsScreenState(
    val isLoading: Boolean = false,
    val items: List<com.example.newsappjetpackcompose.Article>? = emptyList(),
    val error: String? = null,
    val page: Int = 0,
    val endReached:Boolean = false
)