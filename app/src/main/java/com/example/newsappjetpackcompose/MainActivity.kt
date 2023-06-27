package com.example.newsappjetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.newsappjetpackcompose.nav.NavConfiguration
import com.example.newsappjetpackcompose.nav.Screens
import com.example.newsappjetpackcompose.ui.theme.NewsAppJetpackComposeTheme
import com.example.newsappjetpackcompose.view.BottomNavBar
import com.example.newsappjetpackcompose.view.NewsScreenUI
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel
import com.example.newsappjetpackcompose.viewmodel.SearchScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val newsViewModel: NewsViewModel by viewModels()
    private val searchViewModel: SearchScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppJetpackComposeTheme {
//                NewsScreenUI(viewModel)
                  MainScreen(viewModel)
//                Navigation(newsViewModel = viewModel)
            }
        }

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(newsViewModel: NewsViewModel, searchViewModel: SearchScreenViewModel) {
    val navController = rememberNavController()
    val navList = listOf(Screens.NewsScreen,Screens.SearchScreen,Screens.SavedScreen)
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        bottomBar = { BottomNavBar(navController = navController, items = navList)},
        snackbarHost = {SnackbarHost(snackbarHostState)},

    ) {

        //NewsScreenUI(viewModel)
        Surface(modifier = Modifier.fillMaxSize().padding(bottom = it.calculateBottomPadding())) {

            NavConfiguration(navController = navController,
                newsViewModel = newsViewModel,
                searchViewModel = searchViewModel,
                snackbarHostState = snackbarHostState)
        }

    }

}









//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    NewsAppJetpackComposeTheme {
//      MainScreen()
//    }
//}

