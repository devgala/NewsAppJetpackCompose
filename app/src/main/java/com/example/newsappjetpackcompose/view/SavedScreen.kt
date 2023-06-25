package com.example.newsappjetpackcompose.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.newsappjetpackcompose.events.UiEventsSavedScreen
import com.example.newsappjetpackcompose.viewmodel.SavedScreenViewModel


@Composable
fun SavedScreenUI(viewModel : SavedScreenViewModel) {
    val articles = viewModel.articles.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{
            event ->
            when(event){
                is UiEventsSavedScreen.showSnackBar ->{

                }
                is UiEventsSavedScreen.openWebView ->{

                }
            }
        }
    }



    Box (modifier = Modifier.fillMaxSize()){
        Text(text = "SavedScreen", modifier = Modifier.align(Alignment.Center))
    }
}