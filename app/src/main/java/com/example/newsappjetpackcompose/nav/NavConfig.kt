package com.example.newsappjetpackcompose.nav

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsappjetpackcompose.view.NewsScreenUI
import com.example.newsappjetpackcompose.view.SavedScreenUI
import com.example.newsappjetpackcompose.view.SearchScreenUI
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel


@Composable
fun NavConfiguration(
    navController: NavHostController,
viewModel: NewsViewModel,
snackbarHostState: SnackbarHostState
){
  NavHost(navController = navController, startDestination = Screens.NewsScreen.route, builder = {
      composable(Screens.NewsScreen.route){
          NewsScreenUI(newsViewModel = viewModel)

      }
      composable(
          route = Screens.SavedScreen.route,
      ){
          SavedScreenUI(snackbarHostState=snackbarHostState)
      }
      composable(Screens.SearchScreen.route){
          SearchScreenUI()
      }
  })
}