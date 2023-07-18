package com.example.newsappjetpackcompose.bottomNav

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsappjetpackcompose.view.NewsScreenUI
import com.example.newsappjetpackcompose.view.SavedScreenUI
import com.example.newsappjetpackcompose.view.SearchScreenUI
import com.example.newsappjetpackcompose.view.ShortsScreenUI
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel
import com.example.newsappjetpackcompose.viewmodel.SearchScreenViewModel


@Composable
fun NavConfiguration(
    navController: NavHostController,
    newsViewModel: NewsViewModel,
    searchViewModel: SearchScreenViewModel,
    snackbarHostState: SnackbarHostState,
    webNavController: NavController
){
  NavHost(navController = navController, startDestination = Screens.NewsScreen.route, builder = {
      composable(Screens.NewsScreen.route){
          NewsScreenUI(newsViewModel = newsViewModel, snackbarHostState=snackbarHostState, webNavController)
      }
      composable(
          route = Screens.SavedScreen.route,
      ){
          SavedScreenUI(snackbarHostState=snackbarHostState, webNavController = webNavController)
      }
      composable(Screens.SearchScreen.route){
          SearchScreenUI(searchViewModel = searchViewModel,snackbarHostState=snackbarHostState, webNavController = webNavController)

      }
      composable(Screens.ShortsScreen.route){
          ShortsScreenUI(newsViewModel = newsViewModel,snackbarHostState=snackbarHostState, webNavController = webNavController)

      }
  })
}