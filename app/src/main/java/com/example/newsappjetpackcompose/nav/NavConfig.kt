package com.example.newsappjetpackcompose.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsappjetpackcompose.view.NewsScreenNav
import com.example.newsappjetpackcompose.view.NewsScreenUI
import com.example.newsappjetpackcompose.view.SavedScreenUI
import com.example.newsappjetpackcompose.view.SearchScreenUI
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel


@Composable
fun NavConfiguration(
    navController: NavHostController, newsViewModel: NewsViewModel
){
  NavHost(navController = navController, startDestination = Screens.NewsScreenNav.route, builder = {
      composable(Screens.NewsScreenNav.route){
          NewsScreenNav(newsViewModel = newsViewModel)
      }
      composable(Screens.SavedScreen.route){
         SavedScreenUI()
      }
      composable(Screens.SearchScreen.route){
          SearchScreenUI()
      }
  })
}