package com.example.newsappjetpackcompose.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newsappjetpackcompose.NewsResponse
import com.example.newsappjetpackcompose.events.SavedScreenEvents
import com.example.newsappjetpackcompose.events.UiEventsSavedScreen
import com.example.newsappjetpackcompose.repository.NewsRepository
import com.example.newsappjetpackcompose.uicomponents.NewsCard
import com.example.newsappjetpackcompose.uicomponents.ShortsCard
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel
import com.example.newsappjetpackcompose.viewmodel.SavedScreenViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.reflect.KProperty


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ShortsScreenUI(
    newsViewModel: NewsViewModel,
    snackbarHostState: SnackbarHostState,
    webNavController: NavController
) {


    val state = newsViewModel.screenState
    var savedScreenViewModel: SavedScreenViewModel = hiltViewModel()
    LaunchedEffect(key1 = true) {

        newsViewModel.loadNextItems()
        savedScreenViewModel.uiEvent.collect { event ->
            when (event) {
                is UiEventsSavedScreen.showSnackBar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = event.message,
                        // actionLabel = event.action,
                        duration = SnackbarDuration.Short, withDismissAction = true
                    )
                    if (result == SnackbarResult.Dismissed) {
                        savedScreenViewModel.onEvent(SavedScreenEvents.onNotClickUndoAdd)
                    }
                }

                is UiEventsSavedScreen.openWebView -> {

                }
            }
        }
    }
    // Log.d("api check", newsResponse.articles.toString())
    if (state.items == null) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "No news to display", modifier = Modifier.align(Alignment.Center))
        }
    }

    state.items?.let {


        val pagerState = rememberPagerState()
        Box(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                count = state.items.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { index ->
                ShortsCard(
                    article = state.items[index],
                    onEvent = savedScreenViewModel::onEvent,
                    webNavController = webNavController
                )

            }
//            item {
//                CategoryPanel(newsViewModel = newsViewModel)
//            }
//
//            itemsIndexed(
//                state.items
//            ) { index, item ->
//                if (index >= state.items.size - 1 && !state.endReached && !state.isLoading) {
//                    newsViewModel.loadNextItems(newsViewModel.screenState.category)
//                    Log.d("scroll", "this")
//                }
//                NewsCard(item, savedScreenViewModel::onEvent, webNavController = webNavController)
//            }
//
//            if (state.isLoading) {
//
//                item {
//                    Box(Modifier.fillMaxWidth()) {
//
//                        CircularProgressIndicator(
//                            Modifier
//                                .align(Alignment.Center)
//                                .padding(5.dp))
//                    }
//                }
//            }


        }

    }
}

