package com.example.in2000_prosjekt


import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.example.in2000_prosjekt.database.FavoriteViewModel
import com.example.in2000_prosjekt.database.FavoriteViewModelFactory
import com.example.in2000_prosjekt.ui.screens.InfoScreen
import com.example.in2000_prosjekt.ui.screens.SettingsScreen
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
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.Duration.Companion.seconds


class Integrasjonstest {




// Test 8: Test av Apikall med MockEngine, lagt kl.16.30 , den 27.08
    class integratsjonstest_APIkall {
        @get:Rule
        val rule = createComposeRule()
        lateinit var navController: TestNavHostController


        @Test
        fun apiCallForFrost () {

            val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            var favorittscreen = { navController.navigate("Favoritt") }

            rule.setContent {
                IN2000_ProsjektTheme {

                }
            }



            data class frostApiResponse( // Denne klassen er det vi sammenligner vår HTTP request test med. Vi vet at HTTP requesten vil inneholde en parameter med navnet "@type". Variablen i denne klassen skal etterligne det vi forventer HTTP requesten vil respondere med.

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


                suspend fun testGetFrostApiRespons(): frostApiResponse = httpClient.get("https://frost.met.no/sources/v0.jsonld?types=SensorSystem&elements=mean(cloud_area_fraction P1D)&country=norge").body()

                @Test
                fun frostApiTest() {
                    runBlocking {

                        val mockEngine = MockEngine { request ->
                            respond(
                                content = ByteReadChannel(""""@type" : "SourceResponse","""),
                                status = HttpStatusCode.OK,
                            )
                        }
                        val apiClient = ApiClient(mockEngine)

                        Assert.assertEquals("SourceResponse", apiClient.testGetFrostApiRespons().respons) // sammenlinger om verdien til
                    }
                }

            }

        }

    }





}


// Dette er en integrasjonstest: Her så tester vi navigasjonsbaren: Sikt_Bottom_bar, sin navigeringsfunksjonalitet:
//Dette gjør vi først ved å sette startdestinasjon til RulesScreem, som gjøres i : fun testStartDestination ()
// Deretter så Sjekker vi at skjermen har navigert seg: som gjøres i funksjonen     fun testThatScreenChangesOnClickOfBottomBar()
class testNavigationBar {
    @get:Rule
    val rule = createComposeRule()
    lateinit var navController: TestNavHostController

    //@Test
    @Before
    fun setupForNavigationTest() {

        rule.setContent {
            IN2000_ProsjektTheme {

                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())

                var map = { navController.navigate("Map") }
                var favorite = { navController.navigate("Favorite") }
                var rules = { navController.navigate("Info") }
                var settings = {navController.navigate("Settings")}


                val appContext = InstrumentationRegistry.getInstrumentation().targetContext
                val applicationcontext =  LocalContext.current
               // val applicationcontext2 =   Application()
                //val favoriteviewmodel1 :     FavoriteViewModel = viewModel()
                //val favoriteviewmodel2 =    FavoriteViewModel (applicationcontext2)
               // val favoriteviewmodel3 :     FavoriteViewModel = ViewModel() as FavoriteViewModel

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


                    InfoScreen(map, favorite, settings, rules, favoriteViewModel   )
                    //AppNavHost(navController=)


                    NavHost( modifier = Modifier.fillMaxSize(), navController = navController, startDestination = "Info") {
                        composable("Info") { InfoScreen(map, favorite, settings, rules, favoriteViewModel   )        }

                        /*  composable("Alert") {

                               AlertScreen(
                                onNavigateToMap = { map },
                                    onNavigateToFav = { favorite },
                                    onNavigateToRules = rules,
                                    onNavigateToSettings = settings
                                )
                        }*/
                        composable("Settings") { SettingsScreen(map, favorite, settings, rules, favoriteViewModel) }
                    }

                }


            }

        }

    }


    @Test

    fun testStartDestination () {
        val route = navController.currentBackStackEntry?.destination?.route
        Assert.assertEquals(route,"Info" )

    }


        @Test

        fun testThatScreenChangesOnClickOfBottomBar () {

            rule.onAllNodesWithText("Innstillinger")[1].performClick()
          //  rule.onNodeWithText("Innstillinger").performClick()
            //rule.onNodeWithContentDescription(label= "Instillinger knapp").performClick()

            val route = navController.currentDestination?.route
            val route2 = navController.currentBackStackEntry?.destination?.route
            Log.d("endrerouteOnClick", route.toString())

            Log.d("endrerouteOnClicddddddddsdsdsk", route2.toString())
            Assert.assertNotEquals(route,"Info" )

        }

    }








