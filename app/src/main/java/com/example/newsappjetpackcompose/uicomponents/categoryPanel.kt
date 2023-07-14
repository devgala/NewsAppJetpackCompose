package com.example.newsappjetpackcompose.uicomponents

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.capitalize
import com.example.newsappjetpackcompose.util.Constants.Companion.CATEGORIES
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel

@Composable
fun CategoryPanel(newsViewModel: NewsViewModel){
    LazyRow{
        itemsIndexed(CATEGORIES){
            index, item ->  Button(onClick = { newsViewModel.loadNextItems(item) }){
                Text(text = item.capitalize())
            }
        }
    }
}