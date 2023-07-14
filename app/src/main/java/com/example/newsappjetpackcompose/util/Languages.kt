package com.example.newsappjetpackcompose.util
data class Language(val language: String,val code:String);

object Languages {
    val languageList = listOf(
        Language("ARABIC","ar"),
        Language("GERMAN","de"),
        Language("ENGLISH","en"),
        Language("SPANISH","es"),
        Language("FRENCH","fr"),
        Language("HEBREW","he"),
        Language("ITALIAN","it"),
        Language("DUTCH","nl"),
        Language("NORWEGIAN","no"),
        Language("PORTUGUESE","pt"),
        Language("RUSSIAN","ru"),
        Language("SWEDISH","sv"),
        Language("CHINESE","zh"),


    )


}