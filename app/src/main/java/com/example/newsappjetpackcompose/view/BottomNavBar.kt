package com.example.newsappjetpackcompose.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.BottomAppBar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

import com.example.newsappjetpackcompose.bottomNav.Screens

@Composable
fun BottomNavBar(navController: NavHostController,items:List<Screens>) {
    val currentRoute = currentRoute(navController = navController)
    BottomAppBar(

        content = {

            items.forEach {
                NavigationBarItem(selected = currentRoute==it.route, onClick = {
                    if(currentRoute!=it.route) {
                        navController.navigate(it.route)
                    }

                },
                icon = { Icon(imageVector = it.icon, contentDescription = null)},
                    label = { Text(text = it.label)},
                    alwaysShowLabel = it.route==currentRoute,

                    )
            }
        }
    )
}


@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}