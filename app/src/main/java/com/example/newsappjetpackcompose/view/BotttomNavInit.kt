package com.example.newsappjetpackcompose.view

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsappjetpackcompose.bottomNav.NavConfiguration
import com.example.newsappjetpackcompose.bottomNav.Screens
import com.example.newsappjetpackcompose.viewmodel.NewsViewModel
import com.example.newsappjetpackcompose.viewmodel.SearchScreenViewModel
import com.example.newsappjetpackcompose.webViewNav.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavInit(
    newsViewModel: NewsViewModel,
    searchViewModel: SearchScreenViewModel,
    webNavController: NavController
) {
    val navController = rememberNavController()
    val navList = listOf(Screens.NewsScreen, Screens.SearchScreen, Screens.SavedScreen, Screens.ShortsScreen)
    val snackbarHostState = remember { SnackbarHostState() }
    var appBarTitle = rememberSaveable {
        mutableStateOf("News App")
    }
    var topBarState = rememberSaveable {
        mutableStateOf(true)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    when (navBackStackEntry?.destination?.route) {
        Screens.SearchScreen.route -> {
            topBarState.value = false
        }

        Screens.SavedScreen.route -> {
            topBarState.value = true
            appBarTitle.value = "Saved News"
        }

        Screens.NewsScreen.route -> {
            topBarState.value = true
            appBarTitle.value = "News App"
        }
        Screens.ShortsScreen.route->{
            topBarState.value= true
            appBarTitle.value = "Shorts"
        }
    }
    Scaffold(
        bottomBar = { BottomNavBar(navController = navController, items = navList) },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            AnimatedVisibility(visible = topBarState.value) {
                SmallTopAppBar(
                    title = { Text(text = appBarTitle.value) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,

                    ),
                    actions = {
                        IconButton(onClick = {
                            webNavController.navigate(Screen.ProfileScreen.route)
                        }) {
                            Icon(imageVector = Icons.Default.Person, contentDescription = null,tint= Color.White)
                        }
                    }
                )

            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding())
        ) {

            NavConfiguration(
                navController = navController,
                newsViewModel = newsViewModel,
                searchViewModel = searchViewModel,
                snackbarHostState = snackbarHostState,
                webNavController = webNavController
            )
        }
    }

}