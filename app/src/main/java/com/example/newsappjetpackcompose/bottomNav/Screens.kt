package com.example.newsappjetpackcompose.bottomNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val icon:ImageVector,val route:String,val label:String){
    object NewsScreen:Screens(Icons.Default.Home,"NewsScreen","News")
    object SavedScreen:Screens(Icons.Default.Done,"SavedScreen","Saved")
    object SearchScreen:Screens(Icons.Default.Search,"SearchScreen","Search")

    object ShortsScreen:Screens(Icons.Default.Menu,"ShortsScreen","Shorts")

}
