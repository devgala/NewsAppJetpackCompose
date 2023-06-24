package com.example.newsappjetpackcompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articleTable")
data class Article(
    val author:String,
    val title:String,
    val description:String,
    val url:String,
    val urlToImage:String,
    val content:String
){
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}
