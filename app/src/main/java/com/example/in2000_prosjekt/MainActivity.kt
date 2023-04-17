package com.example.in2000_prosjekt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.in2000_prosjekt.ui.theme.IN2000_ProsjektTheme

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.in2000_prosjekt.ui.screens.*

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

    var map = { navController.navigate("Map") }
    var favorite = { navController.navigate("Favorite") }
    var rules = { navController.navigate("Rules") }
    NavHost(modifier = Modifier.fillMaxSize(), navController = navController, startDestination = "Start") {
        composable("Start") { StartPage( onNavigateToNext = { navController.navigate("LandingPage") })  }
        composable("Map") { ShowMap(map, favorite, rules)  }
        composable("Favorite") { FavoriteScreen(onNavigateToMap = map, onNavigateToFav = favorite, onNavigateToRules = rules) }
        composable("Rules") { RulesScreen(map, favorite, rules) }
        composable("API") { API_test(onNavigateToNext = { navController.navigate("API") }) }
        composable("LandingPage") { LandingPage( onNavigateToNext = { navController.navigate("Map") })  }
        composable("Alert") { AlertScreen( onNavigateToMap = { map }, onNavigateToFav = { favorite }, onNavigateToRules = rules) }
    }
}


