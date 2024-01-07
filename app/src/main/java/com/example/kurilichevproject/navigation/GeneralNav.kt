package com.example.kurilichevproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kurilichevproject.Favorites
import com.example.kurilichevproject.InfoView
import com.example.kurilichevproject.OverView
import com.example.kurilichevproject.StartWindow
import com.example.kurilichevproject.db.Landmark
import com.example.kurilichevproject.db.LandmarkImage
import com.example.kurilichevproject.db.LandmarkImages
import com.example.kurilichevproject.db.Landmarks
import com.example.kurilichevproject.db.connectToDB
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

@Composable
fun GeneralNav() {
    LaunchedEffect( null ) {connectToDB()}
    val navController = rememberNavController()
    LaunchedEffect(null) {
        transaction {
            if (SchemaUtils.listTables().size > 15)return@transaction
            SchemaUtils.create(Landmarks, LandmarkImages)
            Landmark.new {
                title = "Эрмитаж"
                address = "Дворцовая пл., д. 1"
                shortDescription = "Эрмитаж это музей с интересной историей"
                detaileDescription =
                    "Высоко в небесах плывет остров облаков, словно кованый из пушистых масс. На его вершинах раскинуты замки из кристаллов, которые ловят лучи солнца и превращают их в мерцающий свет радуги. Вокруг острова вьются игривые облака в различных формах, будто живые существа, плетущие невидимые танцы в воздухе. Легкий ветерок приносит звуки нежной мелодии, создаваемой музыкальными инструментами, слепленными из самого воздуха. Это волшебное место, где сливаются фантазия и реальность, где каждый момент наполнен чудесами."
                isFavorite = false
                comment = ""
            }
            Landmark.new {
                title = "Петропавловская крепость"
                address = "Заячий остров, д. 3"
                shortDescription =
                    "Петропавловская крепость это не только музей, но и место, где можно погулять"
                detaileDescription =
                    "Глубоко в дремучем лесу, среди древних деревьев, возвышается загадочный храм, словно затерянный во времени. Его каменные стены украшены высеченными рельефами, изображающими забытые боги и древние обряды. Внутри храма царит полумрак, прерываемый лишь лучами света, проникающими сквозь тонкие щели в стенах. Алтарь, увенчанный древними символами, являет собой фокусное внимание этого священного места. В воздухе витает таинственная энергия, будто сама суть духовного прошлого оживает в этом забытом храме."
                isFavorite = false
                comment = ""
            }
        }
        transaction {
            LandmarkImage.new {
                landmarkId = Landmark.findById(1)!!
                image = "https://storage.theoryandpractice.ru/tnp/uploads/image_block/000/052/014/image/base_d9dd9b626f.jpg"
            }
            LandmarkImage.new {
                landmarkId = Landmark.findById(2)!!
                image = "https://storage.theoryandpractice.ru/tnp/uploads/image_block/000/052/014/image/base_d9dd9b626f.jpg"
            }
        }
    }

    // Список экранов и навигация между ними
    NavHost(navController = navController, startDestination = "StartWindow") {
        composable("StartWindow") {
            StartWindow(navController)
        }
        composable("OverView") {
            OverView(navController)
        }
        composable("Favorites") {
            Favorites(navController)
        }
        composable("InfoView/{cardIndex}") {

            // Получение индекса карточки из аргументов
            val cardIndex = it.arguments?.getString("cardIndex")?.toInt() ?: 1
            InfoView(navController, transaction {Landmark.findById(cardIndex)!!})
        }
    }
}
