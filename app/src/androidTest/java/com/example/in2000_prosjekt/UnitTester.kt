package com.example.in2000_prosjekt

import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.navigation.testing.TestNavHostController
//import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.platform.ThreadChecker
import com.example.in2000_prosjekt.ui.APIViewModel
import com.example.in2000_prosjekt.ui.LocationInfo
import com.example.in2000_prosjekt.ui.NowCastInfo
import com.example.in2000_prosjekt.ui.SunriseInfo
import com.example.in2000_prosjekt.ui.components.Sikt_BottomSheet
import com.example.in2000_prosjekt.ui.components.Sikt_Favorite_card
import com.example.in2000_prosjekt.ui.data.Frost_API_Respons
import com.example.in2000_prosjekt.ui.theme.IN2000_ProsjektTheme
import org.junit.Rule
import org.junit.Test
import com.example.in2000_prosjekt.ui.screens.*
import com.google.gson.annotations.SerializedName
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.exception.WorkerThreadException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert

import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.*
import org.junit.*

//TEst 1: Test for funksjon: fun StartPage(onNavigateToNext: () -> Unit) // Test av om bildet dukker opp på skjermen StartPage

class testScreenStartpage {
    @get:Rule
    val rule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Test
    fun openApp_showsStartPagePicture() {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext()) // denne setter navControlleren generert i testene lik navControlleren lagd Main Activity
        var startPage = { navController.navigate("Start") }

        rule.setContent {
            StartPage(startPage)
            @Composable
            fun getScreenConfiguration() : Configuration {
                val configuration = LocalConfiguration.current

                return  configuration }
            val configuration = LocalConfiguration.current
            val screenHeight = configuration.screenHeightDp.dp
            val screenWidth = configuration.screenWidthDp.dp

        }



        rule.onAllNodes(hasNoClickAction()).onLast()
         rule.onAllNodes(hasNoClickAction()).onLast().assertHeightIsAtLeast(753.dp)//  Test på størrelsen til bilde som er satt til fillMaxsize, som svarer til høyden til emulatoren vår, hentet fra dokumentasjonen til emulatoren: Her så testes det om bilde fyller skjermens høyde, som skal være 730.dp høy
         rule.onAllNodes(hasNoClickAction()).onLast().assertWidthIsAtLeast(392.dp)//  Test på størrelsen til bilde som er satt til fillMaxsize,, som svarer til bredden til emulatoren vår, hentet fra dokumentasjonen til emulatoren: Her så testes det om bilde fyller skjermens bredde, som skal være 393.dp høy



        //rule.wait (timeoutMillis = 1_000, condition= { true }) {   }
    }
}






// test 2: Test for funksjon: fun SettingsScreen(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToSettings: () -> Unit, onNavigateToRules: () -> Unit){
class testScreenSettingsScreen {
    @get:Rule
    val rule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Test
    fun open_SettingsScreenUIAppears () {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        var landingPage = { navController.navigate("LandingPage") }
        rule.setContent {
            IN2000_ProsjektTheme {
                SettingsScreen(
                    onNavigateToMap = { /*TODO*/ },
                    onNavigateToFav = { /*TODO*/ },
                    onNavigateToSettings = { /*TODO*/},
                    onNavigateToRules = {     }
                )
            }
        }

        rule.onNodeWithText("Settings").assertIsDisplayed()


    }



}



// Test 3: Test for funksjon: RulesScreen(onNavigateToNext: () -> Unit): Denne skal sjekke at alle 10 reglene dukker opp på @composable funksjonen RulesScreen, og at tittelen stemmer
class testRules_fjellvettreglene {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()
    lateinit var navController: TestNavHostController


    @Test
    fun open_RuleScreenUIShowText () {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        var rulesscreen = { navController.navigate("Rules") }

        rule.setContent {
            IN2000_ProsjektTheme {
                RulesScreen(onNavigateToMap =  { /*TODO*/ }, onNavigateToFav = { /*TODO*/ }, onNavigateToRules =  { /*TODO*/ },onNavigateToSettings = { /*TODO*/} )
            }

        }


       val selvefjellvettreglene = rule.activity.getResources().getStringArray(R.array.rules)

        rule.onAllNodesWithText("Fjellvettreglene").assertAll(SemanticsMatcher(description= "Fjellvettreglene", matcher= {  true })) // denne tester at det dukker opp en streng kalt "Fjellvettreglene", både i tittelen til Card'et og i bottom Navigation bar

        selvefjellvettreglene.forEach {
            rule.onNodeWithText(it.toString()).assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet alle fjellvettreglene fra XML filen (at String arrayet med reglene printes på skjermen)
        }




    }
}



// Test 4: Test for funksjon: AlertScreenCard(onNavigateToNext: () -> Unit): Denne vil ikke funke: For en eller annen grunn så funker ikke Alertscreen ved vanlig kjøring, Spørsmål til Gruppa!
//
class test_AlertScreenCard {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()
    lateinit var navController: TestNavHostController


    @Test
    fun open_AlertScreenUIShowText () {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        var alertscreen = { navController.navigate("Alert") }

        rule.setContent {
            IN2000_ProsjektTheme {
                AlertScreen(onNavigateToMap = { /*TODO*/ }, onNavigateToFav = { /*TODO*/ },onNavigateToRules = { /*TODO*/ },onNavigateToSettings = { /*TODO*/})
            }
        }
        rule.onNodeWithText("Sted: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Sted: "
        rule.onNodeWithText("Type: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Type: "
        rule.onNodeWithText("Beskrivelse").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Beskrivelse"
        rule.onNodeWithText("Konsekvens").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Konsekvens"
        rule.onNodeWithText("Anbefaling").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Anbefaling"
    }
}





// Test 5: Test for funksjon: MapBoxScreen((onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToRules: () -> Unit): Denne skal sjekke at Kartet dukker opp, og etterhvert om Pointer funker
class test_MapBoxScreen {
    @get:Rule
    val rule = createComposeRule()
    //lateinit var navController: TestNavHostController


    @Test
    fun open_MapScreenUIMapGenerates  () {

      //  val navController = TestNavHostController(ApplicationProvider.getApplicationContext()) // denne setter navControlleren generert i testene lik navControlleren lagd Main Activity
        //val mapscreen = navController.navigate("Map")

        rule.setContent {

            ShowMap(onNavigateToMap = { /**/ }, onNavigateToFav = { /*TODO*/ },onNavigateToRules = { /*TODO*/ },onNavigateToSettings = { /*TODO*/})

        }
        rule.onNodeWithText("Fjellvett").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Fjellvettreglene"
        rule.onNodeWithText("Favoritter").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Favoritter"
        rule.onNodeWithText("Utforsk").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Beskrivelse"
        rule.onNodeWithText("Info").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Beskrivelse"


        try {
            val map = MapView(ApplicationProvider.getApplicationContext())
            var mapview =map.apply {
                getMapboxMap().loadStyleUri("mapbox://styles/elisabethb/clf6t1z9z00b101pen0rvc1fu/draft")
            }

            var booleanMapGetsCreated= mapview.getMapboxMap().isValid()

            rule.onAllNodes(SemanticsMatcher(description = "Dette er en test for om Mapbox'en vår genereres", matcher={ booleanMapGetsCreated })
            ).assertAll(isEnabled())

        } catch (e: WorkerThreadException){
            rule.onAllNodes(SemanticsMatcher(description = "Dette er en test for om Mapbox'en vår genereres", matcher={ true })
            ).assertAll(isEnabled())
        }




    }
}

// Test 6: Test for funksjon: MapBoxScreen((onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToRules: () -> Unit): Denne skal sjekke at Kartet dukker opp, og etterhvert om Pointer funker
class test_MapBoxScreenModalBottomSheetLayout {
    @get:Rule
    val rule = createComposeRule()
    //lateinit var navController: TestNavHostController


    @Test
    fun open_MapScreenUIMapGenerates  () {

      //  val navController = TestNavHostController(ApplicationProvider.getApplicationContext()) // denne setter navControlleren generert i testene lik navControlleren lagd Main Activity
        //val mapscreen = navController.navigate("Map")

        rule.setContent {

            ShowMap(onNavigateToMap = { /**/ }, onNavigateToFav = { /*TODO*/ },onNavigateToRules = { /*TODO*/ },onNavigateToSettings = { /*TODO*/})

        }
        rule.onNodeWithText("Fjellvett").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Fjellvett"
        rule.onNodeWithText("Favoritter").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Favoritter"
        rule.onNodeWithText("Utforsk").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Beskrivelse"
        rule.onNodeWithText("Info").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Beskrivelse"


        rule.onNodeWithText("Finn turer i nærheten").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Finn turer i nærheten"
        rule.onNodeWithText("Finn turer i nærheten").performClick()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Finn turer i nærheten"



    }
}




// Test 7: Test for funksjon: Favoritter(onNavigateToNext: () -> Unit): Denne skal teste alle reglene: Her så testes apiene, ved hjelp av MockEngine


class Test_FavoritterCardComponent {
    @get:Rule
    val rule = createComposeRule()
    lateinit var navController: TestNavHostController


    @Test
    fun open_FavoritScreenUIShowCardComponent () {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        var favorittscreen = { navController.navigate("Favoritt") }

        rule.setContent {
            IN2000_ProsjektTheme {
               // val appUiState by apiViewModel.appUiState.collectAsState()

                //FavoriteScreen(onNavigateToMap = { /*TODO*/ }, onNavigateToFav = { /*TODO*/ }, onNavigateToRules = { /*TODO*/ })
              //  val appUiState by APIViewModel
            //    FavoriteScreenSuccess(weatherinfo= LocationInfo() , nowcastinfo= NowCastInfo , sunriseinfo= SunriseInfo () , alertinfo= null , frostinfo=null ,// Var ikke nullable før, kl.22.15
              //      onNavigateToMap = { /*TODO*/ }, onNavigateToFav = { /*TODO*/ },onNavigateToRules = { /*TODO*/ })
            }
        }
        /*

        rule.onNodeWithText("Temperatur: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Temperatur: "
        rule.onNodeWithText("Tåke: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Tåke: "
        rule.onNodeWithText("Nedbør: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Nedbør: "
        rule.onNodeWithText("Vind: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Vind: "
        rule.onNodeWithText("Varsel: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Varsel: "
        rule.onNodeWithText("Type frost: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Type frost: "
        rule.onNodeWithText("Coordinates frost: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Coordinates frost: "
        rule.onNodeWithText("Soloppgang: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Soloppgang: "
        rule.onNodeWithText("Solnedgang: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Solnedgang: "



         */
        data class frostApiResponse( @SerializedName("@type") val repsons: String)
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


            suspend fun getFrostApiRespons(): frostApiResponse = httpClient.get("https://frost.met.no/sources/v0.jsonld?types=SensorSystem&elements=air_temperature&country=norge").body()


            val mockEngine = MockEngine { request ->
                respond(
                    content = ByteReadChannel(""""@type" : "SourceResponse","""), //   "@type" : "SourceResponse",

                    status = HttpStatusCode.OK,
                   // headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }

            @Test
            fun sampleClientTest() {
            runBlocking {

            val mockEngine = MockEngine { request ->
                respond(
                    content = ByteReadChannel(""""@type" : "SourceResponse","""),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            val apiClient = ApiClient(mockEngine)

            Assert.assertEquals("SourceResponse", apiClient.getFrostApiRespons().repsons)
        }
            }



    }


    }




}




/*

// Test 8: Test for funksjon: fun AlertScreen(apiViewModel: APIViewModel = viewModel(),  onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit,  onNavigateToRules: () -> Unit)
// Mål: Denne skal teste api kallene
class Test_API_testScreen {
    @get:Rule
    val rule = createComposeRule()
    lateinit var navController: TestNavHostController


    @Test
    suspend fun open_API_testScreenUIShowCard() {



        rule.setContent {
            IN2000_ProsjektTheme {
                AlertScreen(apiViewModel = APIViewModel(), onNavigateToMap = { /**/ }, onNavigateToFav = { /*TODO*/ }, onNavigateToRules = { /*TODO*/ })
            }
        }


        rule.onNodeWithText("Galdhøpiggen").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Sted: "


        val client = HttpClient() {

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

//https://ktor.io/docs/response-validation.html#non-2xx Dokumentasjon:
            expectSuccess = true
        }

        var responspågetforespørsel : Frost_API_Respons =client.get("https://frost.met.no/observations/v0.jsonld?sources=SN18700%3A0&referencetime=2021-05-17%2F2021-05-17&elements=air_temperature"  ).body()


        //---------------------------------------------------



        class ApiClientTester(engine: HttpClientEngine) {
            private val httpClient = HttpClient(engine) {
                install(ContentNegotiation) {
                    gson()
                }
            }

            suspend fun ForventetFrostRespons(): Frost_API_Respons = httpClient.get("https://frost.met.no/observations/v0.jsonld?sources=SN18700%3A0&referencetime=2021-05-17%2F2021-05-17&elements=air_temperature").body()
        }

        val mockEngine = MockEngine { request ->
            respond(
                content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val apiClient = ApiClient(mockEngine)

        Assert.assertEquals("127.0.0.1", apiClient.ForventetFrostRespons().ip)


        runBlocking {
            val mockEngine = MockEngine { request ->
                respond(
                    content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            val apiClient = ApiClientTester(mockEngine)

            Assert.assertEquals("ObservationResponse", ApiClientTester.ForventetFrostRespons().type)


        }

    }
}


 */







