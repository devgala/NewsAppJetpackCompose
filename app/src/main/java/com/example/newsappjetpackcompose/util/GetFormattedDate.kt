package com.example.newsappjetpackcompose.util

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun getFormattedDate(date:String):String
{
    val input = SimpleDateFormat("yyyy-MM-dd")
    val output = SimpleDateFormat("dd/MM/yyyy")
    var d = Date()
    try {
        d = input.parse(date)
    }catch (e: ParseException){
        println(e)
    }
    return output.format(d)?:"Not Available"
}