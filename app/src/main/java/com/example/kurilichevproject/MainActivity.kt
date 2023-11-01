package com.example.kurilichevproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.example.kurilichevproject.ui.theme.KurilichevProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KurilichevProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StartWindow()
                }
            }
        }
    }
}

@Composable
fun StartWindow() {
    Box(modifier = Modifier
        .fillMaxSize().padding(start = 100.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "star",
            tint = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .drawBehind {}) {
        Column(modifier = Modifier.align(Alignment.CenterEnd)) {
            Text(
                text = "Saint Landmark",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Button(
                onClick = { /*Переход на OverView*/ },
                modifier = Modifier
                    .padding(15.dp)
            ) {

                Text("Начать")
            }
        }
    }
}

@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun StartWindowPreview() {
    KurilichevProjectTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            StartWindow()

        }
    }
}