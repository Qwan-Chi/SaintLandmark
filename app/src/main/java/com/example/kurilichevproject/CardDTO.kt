package com.example.kurilichevproject

data class CardDTO(
    val gallery: List<Int>,
    val title: String,
    val address: String,
    val categories: List<String>,
    val shortDescription: String,
    val detaileDescription: String
)
