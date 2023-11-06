package com.example.kurilichevproject

data class CardDTO(val image: Int, val title: String, val address: String)
val cards = listOf(
    CardDTO(R.drawable.star, title = "Тест название", address = "Адрес"),
    CardDTO(R.drawable.star, title = "Тест название", address = "Адрес")
)
