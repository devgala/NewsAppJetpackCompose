package com.example.newsappjetpackcompose.view

import androidx.compose.runtime.Composable
import com.example.newsappjetpackcompose.Navigation
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel

@Composable
fun NewsScreenNav(newsViewModel: NewsViewModel){
    Navigation(newsViewModel = newsViewModel)
}