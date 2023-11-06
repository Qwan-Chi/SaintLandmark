package com.example.kurilichevproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kurilichevproject.CardDTO
import com.example.kurilichevproject.InfoView
import com.example.kurilichevproject.OverView
import com.example.kurilichevproject.R
import com.example.kurilichevproject.StartWindow

/**
 * Навигация между экранами
 * @author Михаил Куриличев (Qwan-Chi)
 * @see StartWindow
 * @see OverView
 * @see InfoView
 */
@Composable
fun GeneralNav() {
    val navController = rememberNavController()

    // Инициализация списка с данными
    val main = listOf(
        CardDTO(
            listOf(R.drawable.star),
            title = "Государственный Эрмитаж",
            address = "Дворцовая пл., д. 1",
            listOf("Категория 1", "Категория 2"),
            "Эрмитаж это музей с интересной историей"
        ), CardDTO(
            listOf(R.drawable.star, R.drawable.ic_launcher_background),
            title = "Петропавловская крепость",
            address = "Заячий остров, д. 3",
            listOf("Категория 3", "Категория 4"),
            "Петропавловская крепость это не только музей, но и место, где можно погулять"
        )
    )

    // Список экранов и навигация между ними
    NavHost(navController = navController, startDestination = "StartWindow") {
        composable("StartWindow") {
            StartWindow(navController)
        }
        composable("OverView") {
            OverView(navController, main)
        }
        composable("InfoView/{cardIndex}") {
            // Получение индекса карточки из аргументов
            val cardIndex = it.arguments?.getString("cardIndex")?.toInt() ?: 0
            InfoView(navController, main[cardIndex])
        }
    }
}