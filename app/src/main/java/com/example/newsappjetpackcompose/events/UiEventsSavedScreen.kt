package com.example.newsappjetpackcompose.events

sealed class UiEventsSavedScreen{
    data class openWebView(val url:String) : UiEventsSavedScreen()
    data class showSnackBar(val message:String,val action:String?=null):UiEventsSavedScreen()
}

