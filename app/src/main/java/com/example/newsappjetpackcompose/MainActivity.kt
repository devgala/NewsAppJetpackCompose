package com.example.newsappjetpackcompose

import android.annotation.SuppressLint
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
import androidx.navigation.compose.currentBackStackEntryAsState
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
                MainScreen(newsViewModel = newsViewModel, searchViewModel = searchViewModel)

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
    var appBarTitle =rememberSaveable{
        mutableStateOf("News App")
    }
    var topBarState = rememberSaveable {
        mutableStateOf(true)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    when(navBackStackEntry?.destination?.route){
        Screens.SearchScreen.route->{
            topBarState.value = false
        }
        Screens.SavedScreen.route->{
            topBarState.value= true
            appBarTitle.value = "Saved News"
        }
        Screens.NewsScreen.route->{
            topBarState.value= true
            appBarTitle.value = "News App"
        }
    }
    Scaffold(
        bottomBar = { BottomNavBar(navController = navController, items = navList)},
        snackbarHost = {SnackbarHost(snackbarHostState)},
        topBar = {
            AnimatedVisibility(visible = topBarState.value) {
                SmallTopAppBar(title = {Text(text = appBarTitle.value)}, colors=TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary, titleContentColor = Color.White))

            }
        }
    ) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding())) {

            NavConfiguration(navController = navController,
                newsViewModel = newsViewModel,
                searchViewModel = searchViewModel,
                snackbarHostState = snackbarHostState

            )
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