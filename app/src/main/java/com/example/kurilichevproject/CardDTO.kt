package com.example.kurilichevproject

import androidx.annotation.DrawableRes

/**
 * Класс доступа к карточкам достопримечательностей
 * @param gallery Список идентификаторов изображений, используемых в галерее. Первая картинка - превью на экране обзора
 * @param title Заголовок достопримечательности
 * @param address Адрес достопримечательности
 * @param categories Список из строк, представляющих категории
 * @param description Описание достопримечательности
 * @author Михаил Куриличев (Qwan-Chi)
 */
data class CardDTO(
    val gallery: List<Int>,
    val title: String,
    val address: String,
    val categories: List<String>,
    val description: String
)
