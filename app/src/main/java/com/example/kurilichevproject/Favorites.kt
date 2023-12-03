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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import com.example.kurilichevproject.db.Landmark
import com.example.kurilichevproject.db.LandmarkImage
import com.example.kurilichevproject.db.LandmarkImages
import com.example.kurilichevproject.db.connectToDB
import com.example.kurilichevproject.ui.theme.AppTheme
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favorites(navController: NavHostController) {
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
    connectToDB()
    // Карточки
    LazyColumn(
        Modifier
            .padding(top = 70.dp, start = 10.dp, end = 10.dp)
    ) {
        val landmarks = transaction { Landmark.all().count().toInt() }
        items(landmarks) { card ->
            OutlinedCard(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    Modifier.clickable { navController.navigate("InfoView/${card}") },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = transaction { LandmarkImage.find(LandmarkImages.landmarkId eq card).first().id.value }),
                        contentDescription = "Изображение-превью карточки",
                        Modifier
                            .size(100.dp)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    )
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(text = transaction { Landmark.findById(card)!!.title }, style = MaterialTheme.typography.headlineSmall)
                        Text(text = transaction { Landmark.findById(card)!!.address }, style = MaterialTheme.typography.labelMedium)
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
                rememberNavController()
            )
        }
    }
}