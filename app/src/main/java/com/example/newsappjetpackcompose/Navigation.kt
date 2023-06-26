package com.example.newsappjetpackcompose

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsappjetpackcompose.nav.Screens
import com.example.newsappjetpackcompose.view.NewsScreenUI
import com.example.newsappjetpackcompose.view.WebViewScreenUI
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel

@Composable
fun Navigation(newsViewModel: NewsViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.NewsScreenUI.route){
        composable(route=Screen.NewsScreenUI.route){
            NewsScreenUI(newsViewModel = newsViewModel, navController = navController)
        }
        composable(
            route = Screen.WebViewScreenUI.route + "/{url}",
        arguments = listOf(
            navArgument("url"){
                type= NavType.StringType
                defaultValue = "https://www.google.com"
                nullable=true
            }
        )){entry ->
            WebViewScreenUI(url = entry.arguments?.getString("url"))
        }
//        composable(route=Screen.WebViewScreen.route){
//            WebViewScreenUI(url = "https://www.google.com")
//        }
    }
}