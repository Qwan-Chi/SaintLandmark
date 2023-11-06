package com.example.kurilichevproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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

/**
 * Экран с обзором всех мест
 * @param navController Контроллер навигации
 * @param cards Список карточек с данными
 * @see CardDTO
 * @see InfoView
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverView(navController: NavHostController, cards: List<CardDTO>) {
    Scaffold(topBar = {
        // Верхняя панель навигации
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(
                    "Обзор",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }, actions = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.TwoTone.Star,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(36.dp),
                        contentDescription = "Localized description"
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
    // Категории
    LazyRow(Modifier.padding(top = 65.dp, start = 10.dp, end = 10.dp)) {
        items(cards) { chip ->
            for (category in chip.categories) {
                AssistChip(
                    onClick = {},
                    label = { Text(text = category) },
                    Modifier.padding(start = 10.dp, top = 5.dp)
                )
            }
        }
    }
    // Карточки
    LazyColumn(
        Modifier
            .padding(top = 110.dp, start = 10.dp, end = 10.dp)
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
fun OverViewPreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            OverView(
                rememberNavController(),
                listOf(
                    CardDTO(
                        gallery = listOf(R.drawable.ic_launcher_background),
                        title = "Государственный Эрмитаж",
                        address = "Дворцовая пл., д. 1",
                        categories = listOf("Категория 1", "Категория 2"),
                        description = "Эрмитаж это музей с интересной историей"
                    ),
                    CardDTO(
                        gallery = listOf(R.drawable.ic_launcher_foreground),
                        title = "Петропавловская крепость",
                        address = "Заячий остров, д. 3",
                        categories = listOf("Категория 3", "Категория 4"),
                        description = "Петропавловская крепость это не только музей, но и место, где можно погулять"
                    )
                )
            )
        }
    }
}