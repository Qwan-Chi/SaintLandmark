package com.example.kurilichevproject

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kurilichevproject.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun InfoView(navController: NavHostController) {
    Scaffold(topBar = {
        // Вверхний бар
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = {
            Text(
                text = "Подробнее",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }, navigationIcon = {
            IconButton(onClick = { navController.navigate("OverView") }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(36.dp),
                    contentDescription = "Localized description"
                )
            }
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            val images = listOf(
                R.drawable.star,
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_background
            )

            val pagerState = rememberPagerState(pageCount = {
                Int.MAX_VALUE
            })
            HorizontalPager(state = pagerState, modifier = Modifier) { page ->
                val normalIndex = page % images.size

                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = images[normalIndex]),
                        contentDescription = null,
                        modifier = Modifier
                            .size(250.dp)
                            .align(Alignment.Center)
                    )
                }
            }
            Row {
                var starStatus by remember { mutableStateOf(false) }
                IconButton(onClick = { starStatus = !starStatus }) {
                    if (!starStatus) {
                        Icon(
                            imageVector = Icons.TwoTone.Star,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(36.dp),
                            contentDescription = "Localized description"
                        )
                    } else Icon(
                        imageVector = Icons.Filled.Star,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(36.dp),
                        contentDescription = "Localized description"
                    )
                }
                Text(
                    text = "Тестовый текст",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            LazyRow(Modifier.fillMaxWidth()) {
                items(chips) { chip ->
                    AssistChip(
                        onClick = {},
                        label = { Text(text = chip.text) },
                        Modifier.padding(start = 10.dp, top = 5.dp)
                    )
                }
            }
            Text(text = "Картельные сговоры не допускают ситуации, при которой сторонники тоталитаризма в науке ассоциативно распределены по отраслям.",
                modifier = Modifier.padding(start = 10.dp, end = 10.dp))
        }
    }
}


@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun InfoViewPreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            InfoView(rememberNavController())
        }
    }
}