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
    val navController = rememberNavController()
    LaunchedEffect(null) {
        connectToDB()
        val tablesLength = transaction { SchemaUtils.listTables().size }
        if (tablesLength > 15) return@LaunchedEffect
        transaction {
            SchemaUtils.create(Landmarks, LandmarkImages)
//заготовка для добавления

//            Landmark.new {
//                title = ""
//                address = ""
//                shortDescription =
//                    ""
//                detaileDescription =
//                    ""
//                isFavorite = false
//                comment = ""
//            }

            Landmark.new {
                title = "Эрмитаж"
                address = "Дворцовая пл., д. 1"
                shortDescription = "Государственный Эрмитаж - крупнейший музей России, расположенный в Санкт-Петербурге. В нем находится около 3 миллионов произведений искусства и памятников мировой культуры"
                detaileDescription =
                    "Подробное описание: Государственный Эрмитаж - одно из крупнейших в мире учреждений подобного рода. Он был основан в 1764 году Екатериной II как частная коллекция произведений искусства. Сегодня в музее насчитывается около трех миллионов произведений искусства и памятников мировой культуры, собранных начиная с каменного века и до нашего столетия. В составе коллекции — живопись, графика, скульптура и предметы прикладного искусства, археологические находки и нумизматический материал. Главный музейный комплекс Эрмитажа расположен в историческом центре Санкт-Петербурга, включает в себя шесть связанных между собой зданий — Зимний дворец, Запасной дом Зимнего дворца, Малый Эрмитаж, Большой (Старый) Эрмитаж, Новый Эрмитаж и Эрмитажный театр. В них открыты для посещения 365 залов. Общая площадь помещений (зданий) Эрмитажа составляет 233 345 м², экспозиционно-выставочная площадь — 100 000 м²"
                isFavorite = false
                comment = ""
            }

            Landmark.new {
                title = "Петропавловская крепость"
                address = "Заячий остров"
                shortDescription =
                    "Петропавловская крепость - старейший памятник архитектуры Санкт-Петербурга, расположенный на Заячьем острове. Она была заложена в 1703 году Петром I для защиты завоеванных земель. Сегодня в крепости находятся многочисленные памятники архитектуры и музеи, такие как Петропавловский собор, Великокняжеская усыпальница, Ботный дом, Комендантский дом, Инженерный дом, Монетный двор, Музей истории Санкт-Петербурга, Военно-исторический музей артиллерии, инженерных войск и войск связи, Музей космонавтики и ракетной техники"
                detaileDescription =
                    "Петропавловская крепость - старейший памятник архитектуры Санкт-Петербурга, расположенный на Заячьем острове. Она была заложена в 1703 году Петром I для защиты завоеванных земель. Крепость относится к исторической части Санкт-Петербурга и вместе с находящимся здесь комплексом памятников входит в список объектов всемирного наследия ЮНЕСКО 2. С первой четверти XVIII века до начала 1920-х годов крепость служила тюрьмой. Сегодня в крепости насчитывается многочисленные памятники архитектуры и музеи, такие как Петропавловский собор, Великокняжеская усыпальница, Ботный дом, Комендантский дом, Инженерный дом, Монетный двор, Музей истории Санкт-Петербурга, Военно-исторический музей артиллерии, инженерных войск и войск связи, Музей космонавтики и ракетной техники. Общая площадь помещений (зданий) крепости составляет 60 000 м², экспозиционно-выставочная площадь — 17 000 м² "
                isFavorite = false
                comment = ""
            }

            Landmark.new {
                title = "Церковь Спаса на Крови"
                address = "наб. канала Грибоедова, 2Б, Санкт-Петербург, Россия, 191186"
                shortDescription =
                    "Церковь Спаса на Крови - одна из самых ярких достопримечательностей Санкт-Петербурга, расположенная на берегу канала Грибоедова. Она была построена на месте убийства императора Александра II в 1881 году. Церковь Спаса на Крови является прекрасным примером русского национального стиля, и ее интерьер украшен мозаикой, созданной мастерами из Венеции"
                detaileDescription =
                    "Подробное описание: Церковь Спаса на Крови - одна из самых ярких достопримечательностей Санкт-Петербурга, расположенная на берегу канала Грибоедова. Она была построена на месте убийства императора Александра II в 1881 году. Церковь Спаса на Крови является прекрасным примером русского национального стиля, и ее интерьер украшен мозаикой, созданной мастерами из Венеции. Внутри церкви находится коллекция произведений искусства, связанных с жизнью и правлением Александра II"
                isFavorite = false
                comment = ""
            }

            Landmark.new {
                title = "Новая Голландия"
                address = "набережная Адмиралтейского канала, 2, Санкт-Петербург, Россия, 190121"
                shortDescription =
                    "Новая Голландия - уникальный парк в Санкт-Петербурге, расположенный на острове в центре города. Он был создан в 2016 году на месте старинных складов корабельного леса. В парке находятся магазины, рестораны, площадки для городских событий и мероприятий, а также пляж и каток"
                detaileDescription =
                    "Подробное описание: Новая Голландия - уникальный парк в Санкт-Петербурге, расположенный на острове в центре города. Он был создан в 2016 году на месте старинных складов корабельного леса. Площадь острова составляет 7,8 гектаров 56. В парке находятся магазины, рестораны, площадки для городских событий и мероприятий, а также пляж и каток. Новая Голландия является одним из самых популярных мест встречи петербуржцев. Здесь можно побродить по шоу-румам, попробовать блюда авторской кухни, насладиться атмосферой этого исторического района"
                isFavorite = false
                comment = ""
            }

            Landmark.new {
                title = "Петергоф"
                address = "ул. Разводная, 2"
                shortDescription =
                    "Петергоф - великолепный дворцово-парковый ансамбль в Санкт-Петербурге, знаменитый своими фонтанами и архитектурным великолепием. Он был основан Петром I в начале 18 века и с тех пор стал одним из самых популярных туристических мест."
                detaileDescription =
                    "Петергоф, также известный как Русский Версаль, представляет собой уникальный дворцово-парковый комплекс, расположенный в нескольких километрах от Санкт-Петербурга. Основанный Петром I в начале 18 века, этот ансамбль включает в себя несколько дворцов, парки с великолепными фонтанами и каскадами. Петергоф является настоящим шедевром архитектуры, окруженным красивыми садами и ухоженными лужайками. Величественные фонтаны, такие как \"Грот\", \"Самсон\", и \"Гранд Каскад\", удивляют посетителей своей величественной красотой. В дворце Петра Великого можно ознакомиться с историей искусства, а также насладиться потрясающим видом на Финский залив Территория Петергофа также предлагает уютные кафе и рестораны, где посетители могут насладиться отличной кухней и отдохнуть после прогулки по паркам и дворцам. Петергоф - это не только историческое место, но и настоящее воплощение роскоши и культуры, привлекающее тысячи туристов каждый год."
                isFavorite = false
                comment = ""
            }

        }

        transaction {
// заготовка для добавления

//            LandmarkImage.new {
//                landmark = Landmark.findById()!!
//                image = ""
//            }

            LandmarkImage.new {
                landmark = Landmark.findById(1)!!
                image = "https://mykaleidoscope.ru/x/uploads/posts/2022-09/1663088296_40-mykaleidoscope-ru-p-ermitazh-sankt-peterburg-instagram-43.jpg"
            }
            LandmarkImage.new {
                landmark = Landmark.findById(1)!!
                image = "https://mykaleidoscope.ru/x/uploads/posts/2022-09/1663088316_52-mykaleidoscope-ru-p-ermitazh-sankt-peterburg-instagram-55.jpg"
            }
            LandmarkImage.new {
                landmark = Landmark.findById(1)!!
                image = "https://sportishka.com/uploads/posts/2022-04/1650715719_8-sportishka-com-p-ermitazh-sankt-peterburg-krasivo-foto-8.jpg"
            }

            LandmarkImage.new {
                landmark = Landmark.findById(2)!!
                image = "https://picture.portalbilet.ru/origin/d58acdb4-bc4f-4e97-b08d-4802856e38c2.jpeg"
            }
            LandmarkImage.new {
                landmark = Landmark.findById(2)!!
                image = "https://mykaleidoscope.ru/x/uploads/posts/2022-09/1663084889_26-mykaleidoscope-ru-p-petropavlovskaya-krepost-krasivo-27.jpg"
            }
            LandmarkImage.new {
                landmark = Landmark.findById(2)!!
                image = "https://avatars.dzeninfra.ru/get-zen_doc/1525719/pub_5fb921c6ffe1de7f5cdbbd3d_5fb927bc9d2ffe38ee6cc42d/scale_1200"
            }

            LandmarkImage.new {
                landmark = Landmark.findById(3)!!
                image = "https://www.fonstola.ru/images/201910/fonstola.ru_348779.jpg"
            }
            LandmarkImage.new {
                landmark = Landmark.findById(3)!!
                image = "https://img.razrisyika.ru/kart/128/1200/508188-spas-na-krovi-3.jpg"
            }
            LandmarkImage.new {
                landmark = Landmark.findById(3)!!
                image = "https://sportishka.com/uploads/posts/2022-04/1650699401_6-sportishka-com-p-khram-spasa-na-krovi-krasivo-foto-6.jpg"
            }

            LandmarkImage.new {
                landmark = Landmark.findById(4)!!
                image = "https://map.autogoda.ru/wp-content/uploads/2023/05/1-8-2.jpg"
            }
            LandmarkImage.new {
                landmark = Landmark.findById(4)!!
                image = "https://foxtime.ru/wp-content/uploads/2019/02/f829422fa20e1e4cbfcabdc8a4f3f734.jpg"
            }
            LandmarkImage.new {
                landmark = Landmark.findById(4)!!
                image = "https://www.op78.ru/wp-content/uploads/2022/12/UQB80qBWL77nSMw5vaUnLewKZuH_orlitu14NVEv2N3VktZzhRfVe2pe6SAv1qB6FFHJ8ApOwbEgliNPPduVtQAK.jpg"
            }

            LandmarkImage.new {
                landmark = Landmark.findById(5)!!
                image = "https://u.9111s.ru/uploads/202309/03/966f5d1e449ec16553c849bd877a50a3.jpg"
            }
            LandmarkImage.new {
                landmark = Landmark.findById(5)!!
                image = "https://baulo.club/uploads/posts/2023-04/1680321569_baulo-club-p-petrograf-dvorets-oboi-26.jpg"
            }
            LandmarkImage.new {
                landmark = Landmark.findById(5)!!
                image = "https://magput.ru/frontend/uploads/editor/f1cd8dad-4395-409d-b12f-7ea530145060.jpg"
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
