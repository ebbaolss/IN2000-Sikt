package com.example.in2000_prosjekt

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.in2000_prosjekt.ui.theme.IN2000_ProsjektTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.in2000_prosjekt.database.FavoriteViewModel
import com.example.in2000_prosjekt.database.FavoriteViewModelFactory
import com.example.in2000_prosjekt.database.MapViewModel
import com.example.in2000_prosjekt.database.MapViewModelFactory
import com.example.in2000_prosjekt.ui.APIViewModel
import com.example.in2000_prosjekt.database.*
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
                    val apiViewModel = APIViewModel()

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
                        val navController = rememberNavController()
                        MultipleScreenApp(favoriteViewModel, mapViewModel, apiViewModel,navController)
                    }
                }
            }
        }
    }
}

@Composable
fun MultipleScreenApp(favoriteViewModel: FavoriteViewModel, mapviewmodel : MapViewModel, apiViewModel: APIViewModel, navController: NavHostController) {



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
    val info = { navController.navigate("Info") {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    } }
    val settings = {navController.navigate("Settings") {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    } }
    
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = "StartPage") {

        composable("StartPage") { StartPage( onNavigateToNext = { navController.navigate("Map") })  }
        composable("Map") { ShowMap(map, favorite, info, settings, mapviewmodel, apiViewModel, favoriteViewModel)  }
        composable("Favorite") { FavoriteScreen(map, favorite, info, settings, favoriteViewModel, apiViewModel) }
        composable("Info") { InfoScreen(map, favorite, info, settings, favoriteViewModel) }
        composable("Settings") { SettingsScreen(map, favorite, info, settings, favoriteViewModel) }
    }
}


