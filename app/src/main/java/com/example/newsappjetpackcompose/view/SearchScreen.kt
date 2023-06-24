package com.example.newsappjetpackcompose.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SearchScreenUI() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "SearchScreen", modifier = Modifier.align(Alignment.Center))
    }
}