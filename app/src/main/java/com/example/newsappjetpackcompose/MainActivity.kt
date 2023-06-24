package com.example.newsappjetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.newsappjetpackcompose.nav.NavConfiguration
import com.example.newsappjetpackcompose.nav.Screens
import com.example.newsappjetpackcompose.ui.theme.NewsAppJetpackComposeTheme
import com.example.newsappjetpackcompose.view.BottomNavBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppJetpackComposeTheme {
                MainScreen()

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