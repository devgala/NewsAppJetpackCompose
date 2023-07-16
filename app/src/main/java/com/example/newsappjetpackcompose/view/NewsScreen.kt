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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newsappjetpackcompose.NewsResponse
import com.example.newsappjetpackcompose.PreferencesManager
import com.example.newsappjetpackcompose.events.SavedScreenEvents
import com.example.newsappjetpackcompose.events.UiEventsSavedScreen
import com.example.newsappjetpackcompose.repository.NewsRepository
import com.example.newsappjetpackcompose.uicomponents.NewsCard
import com.example.newsappjetpackcompose.uicomponents.WeatherDisplay
import com.example.newsappjetpackcompose.util.Languages
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel
import com.example.newsappjetpackcompose.viewmodel.SavedScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.reflect.KProperty


@Composable
fun NewsScreenUI(
    newsViewModel: NewsViewModel,
    snackbarHostState: SnackbarHostState,
    webNavController: NavController
) {

    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val spLanguage = remember { mutableStateOf(preferencesManager.getData("language","")) }
    val weatherData = newsViewModel.weatherResponse.observeAsState()
    val state = newsViewModel.screenState
    val langCode = rememberSaveable { mutableStateOf(Languages.languageCodeMap[spLanguage.value]) }
    var savedScreenViewModel: SavedScreenViewModel = hiltViewModel()
    LaunchedEffect(Unit) {

        newsViewModel.loadNextItems(langCode.value?:"en")
        newsViewModel.loadWeather()
        weatherData.value?.let { Log.d("weather", it.toString()) }
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

    if (state.items.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "No news to display", modifier = Modifier.align(Alignment.Center))
        }
    }

    state.items?.let {

        LazyColumn {
            weatherData.value?.let {

                item{
                    WeatherDisplay(it) {

                        newsViewModel.loadWeather(it)
                    }
                }
            }
            itemsIndexed(
                state.items
            ) { index, item ->
                if (index >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                    newsViewModel.loadNextItems(langCode.value?:"en")
                    Log.d("scroll", "this")
                }
                NewsCard(item, savedScreenViewModel::onEvent, webNavController = webNavController)
            }

            if (state.isLoading) {

                item {
                    Box(Modifier.fillMaxWidth()) {

                        CircularProgressIndicator(
                            Modifier
                                .align(Alignment.Center)
                                .padding(5.dp))
                    }
                }
            }

        }
    }

}

