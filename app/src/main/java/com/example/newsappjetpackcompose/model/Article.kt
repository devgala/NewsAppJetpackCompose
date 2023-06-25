package com.example.newsappjetpackcompose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "articleTable")
data class Article(
    val author:String,
    val title:String,
    val description:String,
    val url:String,
    val urlToImage:String,
    val content:String,
    @Ignore val source:Source
){
    @PrimaryKey(autoGenerate = true) var id:Int = 0
    @ColumnInfo("source_name") var source_name = source.name
}
