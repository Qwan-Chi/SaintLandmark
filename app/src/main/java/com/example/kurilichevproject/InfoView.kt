package com.example.kurilichevproject

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.kurilichevproject.db.Landmark
import com.example.kurilichevproject.db.Landmarks
import com.example.kurilichevproject.ui.theme.AppTheme
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

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

            val pagerState = rememberPagerState(pageCount = { Int.MAX_VALUE })
            HorizontalPager(state = pagerState, modifier = Modifier) { page ->
                val normalIndex = remember {
                    page % transaction {
                        card.images.count().toInt()
                    }
                }
                val imageUrl = remember {
                    transaction { card.images.toList()[normalIndex].image }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .crossfade(true)
                            .build(),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = "Gallery item",
                        modifier = Modifier.fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .align(Alignment.Center)
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
                    modifier = Modifier
                ) {
                    var starStatus by remember { mutableStateOf(card.isFavorite) }
                    IconButton(onClick = {
                        starStatus = !starStatus
                        transaction { card.isFavorite = starStatus }
                    }) {
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
                ) {}
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
                    modifier = Modifier.padding(top = 40.dp, bottom = 10.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = card.detaileDescription
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 6.dp)
            ) {
                var text by remember { mutableStateOf("") }
                LaunchedEffect(null) {
                    transaction {
                        text =
                            Landmarks.select { Landmarks.id eq card.id }.single()[Landmarks.comment]
                    }

                }
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Комментарий") },
                    trailingIcon = {
                        IconButton(onClick = {
                            transaction {
                                // логер
                                addLogger(StdOutSqlLogger)
                                Landmarks.update({ Landmarks.id eq card.id }) {
                                    it[comment] = text
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Send,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp),
                                contentDescription = "Unstarred icon"
                            )
                        }
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
                Landmark.findById(1)!!
            )
        }
    }
}