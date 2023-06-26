package com.example.newsappjetpackcompose

sealed class Screen(val route: String){
    object NewsScreenUI: Screen("NewsScreen")
    object WebViewScreenUI: Screen("WebViewScreenUI")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {arg ->
                append("/$arg")
            }
        }
    }
}
