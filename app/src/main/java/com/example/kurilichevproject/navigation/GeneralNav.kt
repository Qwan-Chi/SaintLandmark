package com.example.kurilichevproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kurilichevproject.CardDTO
import com.example.kurilichevproject.Favorites
import com.example.kurilichevproject.InfoView
import com.example.kurilichevproject.OverView
import com.example.kurilichevproject.R
import com.example.kurilichevproject.StartWindow

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
            shortDescription = "Эрмитаж это музей с интересной историей",
            detaileDescription = "Высоко в небесах плывет остров облаков, словно кованый из пушистых масс. На его вершинах раскинуты замки из кристаллов, которые ловят лучи солнца и превращают их в мерцающий свет радуги. Вокруг острова вьются игривые облака в различных формах, будто живые существа, плетущие невидимые танцы в воздухе. Легкий ветерок приносит звуки нежной мелодии, создаваемой музыкальными инструментами, слепленными из самого воздуха. Это волшебное место, где сливаются фантазия и реальность, где каждый момент наполнен чудесами."
        ), CardDTO(
            listOf(R.drawable.ic_launcher_background, R.drawable.star),
            title = "Петропавловская крепость",
            address = "Заячий остров, д. 3",
            listOf("Категория 3", "Категория 4"),
            shortDescription = "Петропавловская крепость это не только музей, но и место, где можно погулять",
            detaileDescription = "Глубоко в дремучем лесу, среди древних деревьев, возвышается загадочный храм, словно затерянный во времени. Его каменные стены украшены высеченными рельефами, изображающими забытые боги и древние обряды. Внутри храма царит полумрак, прерываемый лишь лучами света, проникающими сквозь тонкие щели в стенах. Алтарь, увенчанный древними символами, являет собой фокусное внимание этого священного места. В воздухе витает таинственная энергия, будто сама суть духовного прошлого оживает в этом забытом храме."
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
        composable("Favorites") {
            Favorites(navController, main)
        }
        composable("InfoView/{cardIndex}") {

            // Получение индекса карточки из аргументов
            val cardIndex = it.arguments?.getString("cardIndex")?.toInt() ?: 0
            InfoView(navController, main[cardIndex])
        }
    }
}