package com.example.newsappjetpackcompose

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsappjetpackcompose.bottomNav.NavConfiguration
import com.example.newsappjetpackcompose.bottomNav.Screens
import com.example.newsappjetpackcompose.ui.theme.NewsAppJetpackComposeTheme
import com.example.newsappjetpackcompose.view.BottomNavBar
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel
import com.example.newsappjetpackcompose.viewmodel.SearchScreenViewModel
import com.example.newsappjetpackcompose.webViewNav.WebViewNav
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val newsViewModel: NewsViewModel by viewModels()
    private val searchViewModel: SearchScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
//            setKeepVisibleCondition{
//               // newsViewModel.isLoading.value
//            }
            setKeepOnScreenCondition{
                newsViewModel.isLoading.value
            }
        }
        setContent {
            NewsAppJetpackComposeTheme {
//                MainScreen(newsViewModel = newsViewModel, searchViewModel = searchViewModel)
                WebViewNav(newsViewModel = newsViewModel, searchViewModel = searchViewModel)
            }
        }

    }
}











@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppJetpackComposeTheme {
     // MainScreen()
    }
}