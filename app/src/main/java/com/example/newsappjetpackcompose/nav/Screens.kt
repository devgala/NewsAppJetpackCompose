package com.example.newsappjetpackcompose.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val icon:ImageVector,val route:String,val label:String){
    object NewsScreen:Screens(Icons.Default.Home,"NewsScreen","News")
    object SavedScreen:Screens(Icons.Default.Done,"SavedScreen","Saved")
    object SearchScreen:Screens(Icons.Default.Search,"SearchScreen","Search")

}
