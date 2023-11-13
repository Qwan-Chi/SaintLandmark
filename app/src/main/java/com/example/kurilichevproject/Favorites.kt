package com.example.kurilichevproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kurilichevproject.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favorites(navController: NavHostController, cards: List<CardDTO>) {
    Scaffold(topBar = {
        // Верхняя панель навигации
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = {
            Text(
                text = "Избранное",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }, navigationIcon = {
            IconButton(onClick = { navController.navigate("OverView") }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(36.dp),
                    contentDescription = "Backwards arrow icon"
                )
            }
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {}
    }
    // Карточки
    LazyColumn(
        Modifier
            .padding(top = 70.dp, start = 10.dp, end = 10.dp)
    ) {
        items(cards) { card ->
            OutlinedCard(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    Modifier.clickable { navController.navigate("InfoView/${cards.indexOf(card)}") },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = card.gallery[0]),
                        contentDescription = "Изображение-превью карточки",
                        Modifier
                            .size(100.dp)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    )
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(text = card.title, style = MaterialTheme.typography.headlineSmall)
                        Text(text = card.address, style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun FavoritesPreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Favorites(
                rememberNavController(),
                listOf(
                    CardDTO(
                        gallery = listOf(R.drawable.ic_launcher_background),
                        title = "Государственный Эрмитаж",
                        address = "Дворцовая пл., д. 1",
                        categories = listOf("Категория 1", "Категория 2"),
                        shortDescription = "Эрмитаж это музей с интересной историей",
                        detaileDescription = "Глубоко в дремучем лесу, среди древних деревьев, возвышается загадочный храм, словно затерянный во времени. Его каменные стены украшены высеченными рельефами, изображающими забытые боги и древние обряды. Внутри храма царит полумрак, прерываемый лишь лучами света, проникающими сквозь тонкие щели в стенах. Алтарь, увенчанный древними символами, являет собой фокусное внимание этого священного места. В воздухе витает таинственная энергия, будто сама суть духовного прошлого оживает в этом забытом храме."
                    ),
                    CardDTO(
                        gallery = listOf(R.drawable.ic_launcher_foreground),
                        title = "Петропавловская крепость",
                        address = "Заячий остров, д. 3",
                        categories = listOf("Категория 3", "Категория 4"),
                        shortDescription = "Петропавловская крепость это не только музей, но и место, где можно погулять",
                        detaileDescription = "Глубоко в дремучем лесу, среди древних деревьев, возвышается загадочный храм, словно затерянный во времени. Его каменные стены украшены высеченными рельефами, изображающими забытые боги и древние обряды. Внутри храма царит полумрак, прерываемый лишь лучами света, проникающими сквозь тонкие щели в стенах. Алтарь, увенчанный древними символами, являет собой фокусное внимание этого священного места. В воздухе витает таинственная энергия, будто сама суть духовного прошлого оживает в этом забытом храме."

                    )
                )
            )
        }
    }
}