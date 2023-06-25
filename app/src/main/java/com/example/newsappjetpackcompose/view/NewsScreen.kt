package com.example.newsappjetpackcompose.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsappjetpackcompose.NewsResponse
import com.example.newsappjetpackcompose.repository.NewsRepository
import com.example.newsappjetpackcompose.uicomponents.NewsCard
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel
import kotlin.reflect.KProperty


@Composable
fun NewsScreenUI(newsViewModel: NewsViewModel) {
    val newsResponse by  newsViewModel.breakingNews.observeAsState(NewsResponse())
    val viewModel = viewModel<NewsViewModel>()
    LaunchedEffect(Unit) {
        viewModel.fetchBreakingNews()
    }
    LazyColumn{
        itemsIndexed(
            newsResponse.articles
        ){
            index, article -> NewsCard(article)
        }
    }

}

