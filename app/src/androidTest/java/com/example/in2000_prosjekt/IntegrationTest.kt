package com.example.in2000_prosjekt


import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.in2000_prosjekt.data.Geometry
import com.example.in2000_prosjekt.data.Properties
import com.example.in2000_prosjekt.database.FavoriteViewModel
import com.example.in2000_prosjekt.database.FavoriteViewModelFactory
import com.example.in2000_prosjekt.database.MapViewModel
import com.example.in2000_prosjekt.database.MapViewModelFactory
import com.example.in2000_prosjekt.ui.APIViewModel
import com.example.in2000_prosjekt.ui.screens.InfoScreen
import com.example.in2000_prosjekt.ui.theme.IN2000_ProsjektTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.gson.gson
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

// The first integration test:
// This tests the Metrological instutes API's has expected results when we make a request that we know the results of.
// This way we ensure the consistency and quality of our api responses
class IntegrationstestAPICall {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testApiCallForLocationForecast () {

        //This class is what we compare our HTTP request test with. We know that the HTTP request will contain a parameter named "@type". The variable in this class should be what we expect the HTTP request to respond with.
        data class Model(
            val type : String?,
            val geometry: Geometry?,
            val properties: Properties?
        )
        class ApiClient(engine: HttpClientEngine) {
            private val client = HttpClient {
                install(ContentNegotiation) {
                    gson()
                }
            }


            private suspend fun clientProxyServerCall(client: HttpClient, URL: String) : HttpResponse {
                return client.get(URL) {
                    headers {
                        append("X-gravitee-api-key", "e4990066-1695-43a6-9ea4-85551da13834")
                    }
                }
            }
            suspend fun testfetchLocationForecast(): Model {


                val url= "https://api.met.no/weatherapi/locationforecast/2.0/complete?lat=60.10&lon=9.58"
                val apicall  = clientProxyServerCall(client, url)
                return apicall.body()

            }

            @Test
            fun apiTest() {
                runBlocking {

                    val mockEngine = MockEngine {
                        respond(
                            content = ByteReadChannel(""""type" : "Feature"""),
                            status = HttpStatusCode.OK,
                        )
                    }
                    val apiClient = ApiClient(mockEngine)

                    Assert.assertEquals("Feature", apiClient.testfetchLocationForecast().type) // The assertion is to compare what  we know the json object will be, with what we actually get from the mockEngine (MockEngine: is a library for mocking API calls)
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
private lateinit var navController: TestNavHostController

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

                MultipleScreenApp(favoriteViewModel, mapViewModel, apiViewModel, navController)
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
    rule.onAllNodesWithText("Innstillinger").onLast().performClick().apply {
        val info = { navController.navigate("Info") }

        rule.runOnUiThread(info)


    }

    //Arrange
    val route = navController.currentDestination?.route


    //Assert
    Assert.assertNotEquals(route, "StartPage")
    Assert.assertEquals(route, "Info")
    rule.onAllNodesWithText("Informasjon").onFirst().assertExists()
}
}









