package com.example.newsappjetpackcompose.webViewNav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsappjetpackcompose.view.BottomNavInit
import com.example.newsappjetpackcompose.view.NewsScreenUI
import com.example.newsappjetpackcompose.view.ProfileScreen
import com.example.newsappjetpackcompose.view.WebViewScreenUI
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel
import com.example.newsappjetpackcompose.viewmodel.SearchScreenViewModel

@Composable
fun WebViewNav(newsViewModel: NewsViewModel, searchViewModel: SearchScreenViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.BottomScreenNav.route){
        composable(route = Screen.BottomScreenNav.route){
            BottomNavInit(newsViewModel = newsViewModel, searchViewModel = searchViewModel, webNavController = navController)
        }
        composable(
            route = "${Screen.WebViewScreenUI.route}/{url}",
        arguments = listOf(
            navArgument("url"){
                type= NavType.StringType
                defaultValue = "https://www.google.com"
            }
        )){entry ->
            entry.arguments?.getString("url")?.let { WebViewScreenUI(url = it) }
        }
    composable(route = Screen.ProfileScreen.route){
        ProfileScreen()
    }
    }
}