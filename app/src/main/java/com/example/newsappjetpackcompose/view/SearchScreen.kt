package com.example.newsappjetpackcompose.view

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.ContentAlpha
import com.example.newsappjetpackcompose.NewsResponse
import com.example.newsappjetpackcompose.uicomponents.NewsCard
import com.example.newsappjetpackcompose.viewmodel.SavedScreenViewModel
import com.example.newsappjetpackcompose.viewmodel.SearchScreenViewModel

@Composable
fun SearchScreenUI(searchViewModel: SearchScreenViewModel) {

    val newsResponse by searchViewModel.searchedNews.observeAsState(NewsResponse())
    val viewModel = viewModel<SearchScreenViewModel>()

    SearchAppBar(onSearchClicked = {
        viewModel.searchQuery = it.text
        viewModel.fetchSearchedNews()
    })

    var savedScreenViewModel: SavedScreenViewModel = hiltViewModel()
    LazyColumn(
        modifier = Modifier
            .padding(60.dp)
    ) {
        itemsIndexed(
            newsResponse.articles
        ) { index, article ->
            NewsCard(article, savedScreenViewModel::onEvent)
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
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    text = "Search here...",
                    color = Color.White
                )
            },
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
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
            )
        )
    }
}