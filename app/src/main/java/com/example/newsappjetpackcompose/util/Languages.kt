package com.example.newsappjetpackcompose.util

data class Language(val language: String, val code: String);

object Languages {
    val languageList = listOf(
        Language("ARABIC", "ar"),
        Language("GERMAN", "de"),
        Language("ENGLISH", "en"),
        Language("SPANISH", "es"),
        Language("FRENCH", "fr"),
        Language("HEBREW", "he"),
        Language("ITALIAN", "it"),
        Language("DUTCH", "nl"),
        Language("NORWEGIAN", "no"),
        Language("PORTUGUESE", "pt"),
        Language("RUSSIAN", "ru"),
        Language("SWEDISH", "sv"),
        Language("CHINESE", "zh"),


        )
    val languageCodeMap = mapOf<String, String>(
        "GERMAN" to "de",
        "ARABIC" to "ar",
        "ENGLISH" to "en",
        "SPANISH" to "es",
        "FRENCH" to "fr",
        "HEBREW" to "he",
        "ITALIAN" to "it",
        "DUTCH" to "nl",
        "NORWEGIAN" to "no",
        "PORTUGUESE" to "pt",
        "RUSSIAN" to "ru",
        "SWEDISH" to "sv",
        "CHINESE" to "zh",
    )


}