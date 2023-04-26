package com.example.in2000_prosjekt

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.in2000_prosjekt.ui.theme.IN2000_ProsjektTheme

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.in2000_prosjekt.ui.database.DatabaseScreenTest
import com.example.in2000_prosjekt.ui.database.FavoriteViewModel
import com.example.in2000_prosjekt.ui.database.FavoriteViewModelFactory
import com.example.in2000_prosjekt.ui.screens.*
import com.example.in2000_prosjekt.ui.theme.Sikt_hvit
import com.example.in2000_prosjekt.ui.theme.Sikt_mørkeblå

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
                        val viewModel: FavoriteViewModel = viewModel(
                            it,
                            "FavoriteViewModel",
                            FavoriteViewModelFactory(
                                LocalContext.current.applicationContext
                                        as Application
                            )
                        )

                        MultipleScreenApp(viewModel)
                    }
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.i("Lifecycle", "onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.i("Lifecycle", "onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.i("Lifecycle", "onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.i("Lifecycle", "onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.i("Lifecycle", "onDestroy")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("Lifecycle", "onRestart")
    }
}

@Composable
fun MultipleScreenApp(viewModel: FavoriteViewModel) {

    val navController = rememberNavController()

    var map = { navController.navigate("Map") {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true} }
    var favorite = { navController.navigate("Favorite") {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    } }
    var rules = { navController.navigate("Rules") {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    } }
    var settings = {navController.navigate("Info") {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    } }
    
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = "Map") {

        composable("StartPage") { StartPage( onNavigateToNext = { navController.navigate("Map") })  }
        composable("Map") { ShowMap(map, favorite, settings, rules)  }
        composable("Favorite") { FavoriteScreen(onNavigateToMap = map, onNavigateToFav = favorite, onNavigateToSettings = settings, onNavigateToRules = rules) }
        composable("Rules") { RulesScreen(map, favorite, settings, rules) }
        composable("Info") { SettingsScreen(map, favorite, settings, rules) }
        composable("Database") { DatabaseScreenTest(viewModel) }
    }
}

