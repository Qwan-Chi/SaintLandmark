package com.example.kurilichevproject

data class NameDescriptionDTO(val mainName: String, val description: String)
val nameAndDescription = listOf(
    NameDescriptionDTO(mainName = "Тестовое название", description = "тестовое описание")
)