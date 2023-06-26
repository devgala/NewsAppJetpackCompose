package com.example.newsappjetpackcompose.view

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsappjetpackcompose.events.SavedScreenEvents
import com.example.newsappjetpackcompose.events.UiEventsSavedScreen
import com.example.newsappjetpackcompose.viewmodel.SavedScreenViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedScreenUI(viewModel: SavedScreenViewModel= hiltViewModel()) {
    val articles = viewModel.articles.collectAsState(initial = emptyList())
   // val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{
            event ->
            when(event){
                is UiEventsSavedScreen.showSnackBar ->{
//                    val result = snackbarHostState.showSnackbar(
//                        message = event.message,
//                        actionLabel = event.action
//                    )
//                    if(result==SnackbarResult.ActionPerformed){
//                        viewModel.onEvent(SavedScreenEvents.onClickUndoDelete)
//                    }

                }
                is UiEventsSavedScreen.openWebView ->{
                    //open webview
                }
            }
        }
    }





    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(articles.value){
            article-> ArticleItemSaved(article = article,
            onEvent = viewModel::onEvent,
            modifier = Modifier.fillMaxWidth()
                .clickable {
                viewModel.onEvent(SavedScreenEvents.onClickArticle(article))
        }
                .padding(10.dp)
        )
        }
    }
}