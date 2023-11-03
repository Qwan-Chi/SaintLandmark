package com.example.kurilichevproject.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kurilichevproject.InfoView
import com.example.kurilichevproject.OverView
import com.example.kurilichevproject.StartWindow

@Composable
fun GeneralNav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "StartWindow"){
        composable("StartWindow"){
            StartWindow(navController)
        }
        composable("OverView"){
            OverView(navController)
        }
        composable("InfoView"){
            InfoView(navController)
        }
    }
}