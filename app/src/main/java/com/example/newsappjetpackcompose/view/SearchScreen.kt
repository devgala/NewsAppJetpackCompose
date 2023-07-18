package com.example.newsappjetpackcompose.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newsappjetpackcompose.NewsResponse
import com.example.newsappjetpackcompose.PreferencesManager
import com.example.newsappjetpackcompose.events.SavedScreenEvents
import com.example.newsappjetpackcompose.events.UiEventsSavedScreen
import com.example.newsappjetpackcompose.uicomponents.NewsCard
import com.example.newsappjetpackcompose.util.Languages
import com.example.newsappjetpackcompose.viewmodel.SavedScreenViewModel
import com.example.newsappjetpackcompose.viewmodel.SearchScreenViewModel

@Composable
fun SearchScreenUI(searchViewModel: SearchScreenViewModel,snackbarHostState:SnackbarHostState, webNavController: NavController) {
    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val spLanguage = remember { mutableStateOf(preferencesManager.getData("language","")) }
   // val newsResponse by searchViewModel.searchedNews.observeAsState(NewsResponse())
    var screenState = searchViewModel.screenState
    val savedScreenViewModel: SavedScreenViewModel = hiltViewModel()
    LaunchedEffect(key1 = true ){

        savedScreenViewModel.uiEvent.collect{
                event->
            when(event){
                is UiEventsSavedScreen.showSnackBar ->{
                    val result  = snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action,
                        duration = SnackbarDuration.Short
                        , withDismissAction = true
                    )
                    if(result== SnackbarResult.Dismissed){
                        savedScreenViewModel.onEvent(SavedScreenEvents.onNotClickUndoAdd)
                    }
                }
                is UiEventsSavedScreen.openWebView->{

                }
            }
        }
    }
    Column {


    SearchAppBar(
        onSearchClicked = {
            if(it.text.isNullOrEmpty()) return@SearchAppBar
            searchViewModel.searchQuery = it.text
            val langCode = Languages.languageCodeMap[spLanguage.value]
             searchViewModel.resetList()
            searchViewModel.paginator.reset()
           searchViewModel.getNewsTest(langCode?:"en")
        },
    )

    screenState.items.let {

        LazyColumn(
            modifier = Modifier
                .padding(top = 10.dp , bottom = 10.dp)
        ) {
            screenState.items?.let {

                itemsIndexed(
                    screenState.items!!
                ) { index, article ->
                    if (index >= screenState.items!!.size - 1 && !screenState.endReached && !screenState.isLoading) {
                        searchViewModel.getNewsTest()
                        Log.d("scroll", "this")
                    }
                    NewsCard(article, savedScreenViewModel::onEvent, webNavController = webNavController)


                }
                if(screenState.isLoading){

                    item{
                        Box(modifier = Modifier.fillMaxWidth()) {

                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                        }

                    }
                }
            }
        }
    }
    }
}

@Composable
fun SearchAppBar(
    onSearchClicked: (TextFieldValue) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            , color = MaterialTheme.colorScheme.primary,
    ) {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        TextField(modifier = Modifier
            .fillMaxWidth(),
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            placeholder = {
                Text(
                    modifier = Modifier,
                    text = "Search here...",
                    color = Color.White
                )
            },
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier,
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.toString().isNotBlank()) {
                            onSearchClicked(text)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.White, containerColor = MaterialTheme.colorScheme.primary, cursorColor =Color.White )
        )
    }
}