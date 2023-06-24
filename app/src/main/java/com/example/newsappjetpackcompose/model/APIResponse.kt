package com.example.newsappjetpackcompose.model

data class APIResponse(
    val status:String,
    val totalResults:Int,
    val articles:List<Article>

)
