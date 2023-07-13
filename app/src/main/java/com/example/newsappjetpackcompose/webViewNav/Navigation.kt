package com.example.newsappjetpackcompose.webViewNav

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsappjetpackcompose.view.BottomNavInit
import com.example.newsappjetpackcompose.view.NewsScreenUI
import com.example.newsappjetpackcompose.view.SignInScreen
import com.example.newsappjetpackcompose.view.SignUpScreen
import com.example.newsappjetpackcompose.view.WebViewScreenUI
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel
import com.example.newsappjetpackcompose.viewmodel.SearchScreenViewModel
import com.example.newsappjetpackcompose.viewmodel.SignInViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun WebViewNav(
    newsViewModel: NewsViewModel,
    searchViewModel: SearchScreenViewModel
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SignInScreen.route
    ){
//        composable(route= Screen.NewsScreenUI.route){
//            NewsScreenUI(newsViewModel = newsViewModel, navController = navController)
//        }
        composable(route = Screen.SignInScreen.route){
            SignInScreen()
        }
        composable(route = Screen.SignUpScreen.route){
            SignUpScreen(navController)
        }
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
//        composable(route=Screen.WebViewScreen.route){
//            WebViewScreenUI(url = "https://www.google.com")
//        }
    }
}