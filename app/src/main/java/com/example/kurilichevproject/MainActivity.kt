package com.example.kurilichevproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kurilichevproject.nav.GeneralNav
import com.example.kurilichevproject.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GeneralNav()
                }
            }
        }
    }
}

@Composable
fun StartWindow(navController: NavHostController) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "star",
            tint = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
                .size(this.maxHeight)
                .align(Alignment.CenterEnd)
                .offset(this.maxWidth / 2)
        )
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .drawBehind {}) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(end = 40.dp)
        ) {
            Text(
                modifier = Modifier,
                text = "Saint\nLandmark",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Button(
                onClick = { navController.navigate("OverView") },
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text("Начать")
            }
        }
    }
}

@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun StartWindowPreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            StartWindow(rememberNavController())
        }
    }
}