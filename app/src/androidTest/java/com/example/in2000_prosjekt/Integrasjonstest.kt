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


class Integrasjonstest {




// The first integration test:
// This tests the Metrological instutes API's has expected results when we make a request that we know the results of.
// This way we ensure the consistency and quality of our api responses
    class IntegrationstestAPICall {
        @get:Rule
        val rule = createComposeRule()

        @Test
        fun apiCallForFrost () {

            data class FrostApiResponse( //This class is what we compare our HTTP request test with. We know that the HTTP request will contain a parameter named "@type". The variable in this class should be what we expect the HTTP request to respond with.

                @SerializedName("@type") val respons: String

            )


            class ApiClient(engine: HttpClientEngine) {
                private val httpClient = HttpClient(engine) {
                    install(Auth) {
                        basic {
                            credentials {
                                BasicAuthCredentials(
                                    username = "1cf3b8eb-0fbd-46c9-803d-32206f191ccf",
                                    password = ""
                                )
                            }
                        }
                    }
                    install(ContentNegotiation) {
                        gson()
                    }
                }


                suspend fun testGetFrostApiRespons(): FrostApiResponse = httpClient.get("https://frost.met.no/sources/v0.jsonld?types=SensorSystem&elements=mean(cloud_area_fraction P1D)&country=norge").body()

                @Test
                fun frostApiTest() {
                    runBlocking {

                        val mockEngine = MockEngine {
                            respond(
                                content = ByteReadChannel(""""@type" : "SourceResponse","""),
                                status = HttpStatusCode.OK,
                            )
                        }
                        val apiClient = ApiClient(mockEngine)

                        Assert.assertEquals("SourceResponse", apiClient.testGetFrostApiRespons().respons) // The assertion is to compare what  we know the json object will be, with what we actually get from the mockEngine (MockEngine: is a library for mocking API calls)
                    }
                }

            }

        }

    }





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

                    MultipleScreenApp(favoriteViewModel, mapViewModel, apiViewModel,navController)
                    InfoScreen(map, favorite, settings, info, favoriteViewModel   )



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








