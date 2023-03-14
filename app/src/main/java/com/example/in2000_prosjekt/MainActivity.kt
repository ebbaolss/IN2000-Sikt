package com.example.in2000_prosjekt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.in2000_prosjekt.ui.theme.IN2000_ProsjektTheme

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.in2000_prosjekt.ui.screens.FavoriteScreen
import com.example.in2000_prosjekt.ui.screens.RulesScreen
import com.example.in2000_prosjekt.ui.screens.ShowMap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IN2000_ProsjektTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MultipleScreenApp()
                }
            }
        }
    }
}

@Composable
fun MultipleScreenApp() {
    val navController = rememberNavController()

    NavHost(modifier = Modifier.fillMaxSize(), navController = navController, startDestination = "Map") {
        composable("Map") { ShowMap( onNavigateToNext = { navController.navigate("Favorite") })  }
        composable("Favorite") { FavoriteScreen(onNavigateToNext = { navController.navigate("Rules") }) }
        composable("Rules") { RulesScreen(onNavigateToNext = { navController.navigate("Rules") }) }
    }
}

