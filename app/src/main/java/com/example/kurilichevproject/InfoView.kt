package com.example.kurilichevproject

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kurilichevproject.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun InfoView(navController: NavHostController, card: Landmark) {
    Scaffold(topBar = {
        // Верхняя панель навигации
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = {
            Text(
                text = "Подробнее",
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
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {


            val pagerState = rememberPagerState(pageCount = {
                Int.MAX_VALUE
            })
            HorizontalPager(state = pagerState, modifier = Modifier) { page ->
                val normalIndex = page % LandmarkImage.find { LandmarkImages.landmarkId eq card.id.value }
                    .count().toInt()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    Image(
                        painter = painterResource(id = LandmarkImage.findById(normalIndex)!!.image.toInt()),
                        contentDescription = "Gallery item",
                        modifier = Modifier
                            .size(250.dp)
                            .align(Alignment.Center)
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
            }
            Column(
                Modifier
                    .padding(10.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    var starStatus by remember { mutableStateOf(false) }
                    IconButton(onClick = { starStatus = !starStatus }) {
                        if (!starStatus) {
                            Icon(
                                imageVector = Icons.TwoTone.Star,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp),
                                contentDescription = "Unstarred icon"
                            )
                        } else Icon(
                            imageVector = Icons.Filled.Star,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp),
                            contentDescription = "Starred icon"
                        )
                    }

                    Text(
                        text = card.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = card.shortDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 10.dp)
                )

                LazyRow(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Map,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp),
                        contentDescription = "Starred icon"
                    )
                    Text(
                        text = "Адрес: " + card.address,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                    )
                }
                Text(
                    text = "Подробное описание",
                    modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = card.detaileDescription
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally).
                padding(bottom = 6.dp)
            ) {
                var text by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Комментарий") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Send,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp),
                            contentDescription = "Unstarred icon"
                        )
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun InfoViewPreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            InfoView(
                rememberNavController(),
                Landmark.new {
                    title = "Я КУРЮ ЖИРНЫЙ ЧЛЕН"
                    address = "Дворцовая пл., д. 1"
                    shortDescription = "Эрмитаж это музей с интересной историей"
                    detaileDescription = "Высоко в небесах плывет остров облаков, словно кованый из пушистых масс. На его вершинах раскинуты замки из кристаллов, которые ловят лучи солнца и превращают их в мерцающий свет радуги. Вокруг острова вьются игривые облака в различных формах, будто живые существа, плетущие невидимые танцы в воздухе. Легкий ветерок приносит звуки нежной мелодии, создаваемой музыкальными инструментами, слепленными из самого воздуха. Это волшебное место, где сливаются фантазия и реальность, где каждый момент наполнен чудесами."
                    isFavorite = false
                    comment = ""
                }
            )
        }
    }
}