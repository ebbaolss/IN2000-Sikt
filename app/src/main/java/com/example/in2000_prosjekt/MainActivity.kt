package com.example.in2000_prosjekt

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.in2000_prosjekt.ui.theme.IN2000_ProsjektTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.in2000_prosjekt.ui.APIViewModel
import com.example.in2000_prosjekt.ui.database.*
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

                    val owner = LocalViewModelStoreOwner.current

                    owner?.let {
                        val favoriteViewModel: FavoriteViewModel = viewModel(
                            it,
                            "FavoriteViewModel",
                            FavoriteViewModelFactory(
                                LocalContext.current.applicationContext
                                        as Application
                            )
                        )

                        val mapViewModel: MapViewModel = viewModel(
                            it,
                            "MapViewModel",
                            MapViewModelFactory(
                                LocalContext.current.applicationContext as Application
                            )
                        )

                        MultipleScreenApp(favoriteViewModel, mapViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun MultipleScreenApp(viewModel: FavoriteViewModel, mapviewmodel : MapViewModel) {

    val navController = rememberNavController()

    val map = { navController.navigate("Map") {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // Re-selecting the same item
        launchSingleTop = true
        // Restore state when re-selecting a previously selected item
        restoreState = true} }
    val favorite = { navController.navigate("Favorite") {

        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    } }
    val rules = { navController.navigate("Rules") {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    } }
    val settings = {navController.navigate("Info") {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    } }
    
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = "Map") {

        composable("StartPage") { StartPage( onNavigateToNext = { navController.navigate("Map") })  }

        composable("Favorite") { FavoriteScreen(onNavigateToMap = map, onNavigateToFav = favorite, onNavigateToSettings = settings, onNavigateToRules = rules, viewModel = viewModel) }
        composable("Map") { ShowMap(map, favorite, settings, rules, mapviewmodel)  }
        composable("Rules") { RulesScreen(map, favorite, settings, rules) }
        composable("Info") { SettingsScreen(map, favorite, settings, rules) }
        composable("Database") { DatabaseScreenTest(viewModel) }
    }
}


