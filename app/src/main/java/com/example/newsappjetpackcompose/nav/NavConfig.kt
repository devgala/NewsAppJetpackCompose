package com.example.newsappjetpackcompose.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsappjetpackcompose.view.NewsScreenUI
import com.example.newsappjetpackcompose.view.SavedScreenUI
import com.example.newsappjetpackcompose.view.SearchScreenUI
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel


@Composable
fun NavConfiguration(
    navController: NavHostController
){
  NavHost(navController = navController, startDestination = Screens.NewsScreen.route, builder = {
      composable(Screens.NewsScreen.route){
          SavedScreenUI()
      }
      composable(Screens.SavedScreen.route){
         SavedScreenUI()
      }
      composable(Screens.SearchScreen.route){
          SearchScreenUI()
      }
  })
}