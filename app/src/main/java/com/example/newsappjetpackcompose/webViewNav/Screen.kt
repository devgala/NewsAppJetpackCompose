package com.example.newsappjetpackcompose.webViewNav

sealed class Screen(val route: String){
    object BottomScreenNav: Screen("BottomScreenNav")
    object WebViewScreenUI: Screen("WebViewScreenUI")
    object LoginScreen: Screen("LoginScreen")
    object SignUpScreen: Screen("SignUpScreen")

//    fun withArgs(vararg args: String): String{
//        return buildString {
//            append(route)
//            args.forEach {arg ->
//                append("/$arg")
//            }
//        }
//    }
}
