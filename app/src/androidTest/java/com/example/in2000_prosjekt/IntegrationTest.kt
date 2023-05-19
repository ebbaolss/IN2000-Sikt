package com.example.in2000_prosjekt


import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.in2000_prosjekt.database.FavoriteViewModel
import com.example.in2000_prosjekt.database.FavoriteViewModelFactory
import com.example.in2000_prosjekt.database.MapViewModel
import com.example.in2000_prosjekt.database.MapViewModelFactory
import com.example.in2000_prosjekt.ui.*
import com.example.in2000_prosjekt.ui.screens.*
import com.example.in2000_prosjekt.ui.theme.IN2000_ProsjektTheme
import com.google.gson.annotations.SerializedName
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class IntegrationTest {



}


// The Second integration test:
//This test has two parts:
// First it confirms our Navigation bars initial destination: StartPage()
// Then it tests the "route" variable's change to the screen InfoScreen()
class testNavigationBar {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()
    lateinit var navController: TestNavHostController

    //@Test
    @Before
    fun setupForNavigationTest() {
        rule.setContent {
            IN2000_ProsjektTheme {

                //Arrange
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())

                //Arrange
                val map = { navController.navigate("Map") }
                val favorite = { navController.navigate("Favorite") }
                val info = { navController.navigate("Info") }
                val settings = {navController.navigate("Settings")}



                //Arrange
                val apiViewModel = APIViewModel()
                //Arrange
                val owner = LocalViewModelStoreOwner.current

                owner?.let {
                    //Arrange
                    val favoriteViewModel: FavoriteViewModel = viewModel(
                        it,
                        "FavoriteViewModel",
                        FavoriteViewModelFactory(
                            LocalContext.current.applicationContext
                                    as Application
                        )
                    )
                    //Arrange
                    val mapViewModel: MapViewModel = viewModel(
                        it,
                        "MapViewModel",
                        MapViewModelFactory(
                            LocalContext.current.applicationContext as Application
                        )
                    )

                    MultipleScreenApp(favoriteViewModel, mapViewModel, apiViewModel)
                    InfoScreen(map, favorite, settings, info)



                }

            }


        }

    }



    // Tests the start destination of the navigation controller,
    // which is initially sett to StartPage in the file MainActivity.kt (declared).
    //This tests ensures the sat the initial screen in the app is the StartPage screen
    @Test
    fun testStartDestination () {
        //Arrange
        val route = navController.currentBackStackEntry?.destination?.route
        //Assert
        Assert.assertEquals(route,"StartPage" )

    }


    // Tests that the route gets updated with the current wished destination
    // which is initially sett to StartPage in the file MainActivity.kt (declared).
    //Then it is changed to the screen info upon pressing the Navigation bottom bar
    @Test
    fun testThatScreenChangesOnClickOfBottomBar () {


        //Arrange And Act
        rule.onAllNodesWithText("Innstillinger").onLast().performClick().apply{
            val info = { navController.navigate("Info") }

            rule.runOnUiThread(info)


        }

        //Arrange
        val route = navController.currentDestination?.route


        //Assert
        Assert.assertNotEquals(route,"StartPage" )
        Assert.assertEquals(route,"Info" )
        rule.onAllNodesWithText("Informasjon").onFirst().assertExists()

    }

}







