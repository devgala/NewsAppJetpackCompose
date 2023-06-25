package com.example.newsappjetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.newsappjetpackcompose.nav.NavConfiguration
import com.example.newsappjetpackcompose.nav.Screens
import com.example.newsappjetpackcompose.repository.NewsRepository
import com.example.newsappjetpackcompose.ui.theme.NewsAppJetpackComposeTheme
import com.example.newsappjetpackcompose.view.BottomNavBar
import com.example.newsappjetpackcompose.view.NewsScreenUI
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppJetpackComposeTheme {
                NewsScreenUI(viewModel)

            }
        }

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navList = listOf(Screens.NewsScreen,Screens.SearchScreen,Screens.SavedScreen)
    Scaffold(
        bottomBar = { BottomNavBar(navController = navController, items = navList)}
    ) {
        NavConfiguration(navController = navController)
    }

}








@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppJetpackComposeTheme {
      MainScreen()
    }
}