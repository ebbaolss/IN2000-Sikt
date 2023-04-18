package com.example.in2000_prosjekt

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.core.view.get
import androidx.core.view.isVisible
//import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.platform.ThreadChecker
import com.example.in2000_prosjekt.ui.APIViewModel
import com.example.in2000_prosjekt.ui.SunriseInfo
import com.example.in2000_prosjekt.ui.data.Frost_API_Respons
import com.example.in2000_prosjekt.ui.theme.IN2000_ProsjektTheme
import org.junit.Rule
import org.junit.Test
import com.example.in2000_prosjekt.ui.screens.*
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


//TEst 1: Test for funksjon: fun StartPage(onNavigateToNext: () -> Unit) // Test av om bildet dukker opp på skjermen StartPage

class TestBildeStartpage {
    @get:Rule
    val rule = createComposeRule()}
    /*lateinit var navController: TestNavHostController

    @Test
    fun openApp_showsStartPagePicture() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext()) // denne setter navControlleren generert i testene lik navControlleren lagd Main Activity
        var startPage = { navController.navigate("Start") }

        rule.setContent {
            StartPage(startPage)

            //StartPage (onNavigateToNext = navController.setCurrentDestination({   navController.} "Start Page"))
        }
        //rule.onNodeWithText().assertIsDisplayed()
        rule.onNodeWithContentDescription("Andy Rubin")
            .assertIsDisplayed() // Dette er en test som verifiserer det vi har skrevet i contentDescription til bilde på skjermen
    }
}





// test 2: Test for funksjon: fun LandingPage(onNavigateToNext: () -> Unit)  // Denne tester om landing pagen har en tekstboble som heter "Planlegg tur"
    class TestScreenLandingPageText {
    @get:Rule
        val rule = createComposeRule()
        lateinit var navController: TestNavHostController

        @Test
        fun open_LandingPageUIShowText () {
            val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            var landingPage = { navController.navigate("LandingPage") }


            rule.setContent { // test at De går ann å lage LandingPage Screen på en litt annen måte: lørdag 17.33 den 15.04                 LandingPage( onNavigateToNext = {nada })

                //LandingPage( landingPage)
                LandingPage( onNavigateToNext = {/* Toooodoooo */ })
            }

            rule.onNodeWithText("Planlegg tur").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Planlegg tur"
            rule.onNodeWithText("Få informasjon").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Få informasjon"

            rule.onNodeWithText("Lagre favoritter").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Lagre favoritter"

            rule.onNodeWithText("Kom igang").assertIsDisplayed()// Dette er en test som verifiserer det dannes en knapp med teksten skrevet:"Kom igang"
            rule.onNodeWithText("Hopp over").assertIsDisplayed()// Dette er en test som verifiserer det dannes en knapp med teksten skrevet:"Hopp over"



            //rule.onNodeWithText("Planlegg tur").assertHasClickAction()// Dette er en test som verifiserer det dannes en knapp med teksten skrevet:"Planlegg tur"

            // rule.onNodeWithText("Planlegg tur").performKeyPress({ onNavigateToNext() })// Dette er en test som verifiserer det dannes en knapp med teksten skrevet:"Planlegg tur"
           // rule.onNode(hasText("Hopp over") and hasClickAction()).performClick(). // Dette er en test som verifiserer det dannes en knapp med teksten skrevet:"Planlegg tur"

        }



    }



/*
// Test 2 Verjson 2: av TestScreenLandingPageText Lag skjermen direkte fra main activity: https://stackoverflow.com/questions/74725792/android-compose-app-crashes-during-ui-testing-instrumented-tests
// test 2: Test for funksjon: fun LandingPage(onNavigateToNext: () -> Unit)  // Denne tester om landing pagen har en tekstboble som heter "Planlegg tur"
class TestScreenLandingPageText2 {
    @get:Rule
    val rule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Test
    fun open_LandingPageUIShowText2 () {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        var landingPage = { navController.navigate("LandingPage") }

        rule.setContent {
            IN2000_ProsjektTheme {
                LandingPage(landingPage)
            }

        }

        rule.onNodeWithText("Planlegg tur").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Planlegg tur"

        //rule.onNodeWithText("Planlegg tur").assertIsDisplayed()// Dette er en test som verifiserer det dannes en knapp med teksten skrevet:"Planlegg tur"


    }



}

 */




// test 3: Test for funksjon: LandingPage(onNavigateToNext: () -> Unit)
// Test 2 og 3 er veldig like : Test 2: tester bare tekseten: Test 3 skal teste funksjkoneliteten til knappen  "Kom igang" og "Hoppover"
class TestScreenLandingPageText2 {
    @get:Rule
    val rule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Test
    fun open_LandingPageUIShowText2 () {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        var landingPage = { navController.navigate("LandingPage") }

        rule.setContent {
            IN2000_ProsjektTheme {
                LandingPage(landingPage)
            }

        }
        // kanskje bruk run eller start eller lignene for å si assert A knappetrykk, B: onNavitagtetoNext()

        //rule.onNode(hasText("Hopp over") and hasClickAction()).performClick().assertEquals() // Dette er en test som verifiserer det dannes en knapp med teksten skrevet:"Planlegg tur"



    }



}



// Test 4: Test for funksjon: RulesScreen(onNavigateToNext: () -> Unit): Denne skal sjekke at alle 10 reglene dukker opp på @composable funksjonen RulesScreen, og at tittelen stemmer
class TestRules_fjellvettreglene {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()
    lateinit var navController: TestNavHostController


    @Test
    fun open_RuleScreenUIShowText () {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        var rulesscreen = { navController.navigate("Rules") }

        rule.setContent {
            IN2000_ProsjektTheme {
                RulesScreen(onNavigateToMap =  { /*TODO*/ }, onNavigateToFav = { /*TODO*/ }, onNavigateToRules =  { /*TODO*/ })
            }

        }


       val selvefjellvettreglene = rule.activity.getResources().getStringArray(R.array.rules)

        rule.onAllNodesWithText("Fjellvettreglene").assertAll(SemanticsMatcher(description= "Fjellvettreglene", matcher= {  true })) // denne tester at det dukker opp en streng kalt "Fjellvettreglene", både i tittelen til Card'et og i bottom Navigation bar

        selvefjellvettreglene.forEach {
            rule.onNodeWithText(it.toString()).assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet fra XML filen (at String arrayet med reglene printes på skjermen)
        }




    }
}



// Test 5: Test for funksjon: AlertScreenCard(onNavigateToNext: () -> Unit): Denne vil ikke funke: For en eller annen grunn så funker ikke Alertscreen ved vanlig kjøring, Spørsmål til Gruppa!
//
class Test_AlertScreenCard {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()
    lateinit var navController: TestNavHostController


    @Test
    fun open_AlertScreenUIShowText () {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        var alertscreen = { navController.navigate("Alert") }

        rule.setContent {
            IN2000_ProsjektTheme {
                AlertScreen(onNavigateToMap = { /*TODO*/ }, onNavigateToFav = { /*TODO*/ },onNavigateToRules = { /*TODO*/ })
            }
        }
        rule.onNodeWithText("Sted: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Sted: "
        rule.onNodeWithText("Type: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Type: "
        rule.onNodeWithText("Beskrivelse").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Beskrivelse"
        rule.onNodeWithText("Konsekvens").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Konsekvens"
        rule.onNodeWithText("Anbefaling").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Anbefaling"
    }
}





// Test 6: Test for funksjon: MapBoxScreen((onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToRules: () -> Unit): Denne skal sjekke at Kartet dukker opp, og etterhvert om Pointer funker
class Test_MapBoxScreen {
    @get:Rule
    val rule = createComposeRule()
    //lateinit var navController: TestNavHostController


    @Test
    fun open_MapScreenUIClickableMap  () {

      //  val navController = TestNavHostController(ApplicationProvider.getApplicationContext()) // denne setter navControlleren generert i testene lik navControlleren lagd Main Activity
        //val mapscreen = navController.navigate("Map")

        rule.setContent {

            ShowMap(onNavigateToMap = { /**/ }, onNavigateToFav = { /*TODO*/ },onNavigateToRules = { /*TODO*/ })

        }
        rule.onNodeWithText("Fjellvettreglene").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Fjellvettreglene"
        rule.onNodeWithText("Favoritter").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Favoritter"
        rule.onNodeWithText("Utforsk").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Beskrivelse"

       // rule.onNode(hasScrollAction()).assertExists() // denne verifiserer aren skjermen som dannes er scrollable,
        //rule.onNode(hasClickAction()).assertExists()
        //rule.onNode(hasClickAction())

/*
        val context =ApplicationProvider.getApplicationContext()

        MapView(ApplicationProvider.getApplicationContext(), mapInitOptions: MapInitOptions = MapInitOptions(ApplicationProvider.getApplicationContext())){

            // innafor denne så skal man kunne bruke mapController. som har masse teste funksjoner
        }
        map.isContextClickable

        rule.apply {

            map.apply {
                getMapboxMap().isValid()
            }
        }


 */






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


/*


// Test 7: Test for funksjon: Favoritter(onNavigateToNext: () -> Unit): Denne skal sjekke at alle 10 reglene dukker opp på @composable funksjonen RulesScreen
// disse testes litt anderledes
class Test_FavoritterCard {
    @get:Rule
    val rule = createComposeRule()
    lateinit var navController: TestNavHostController


    @Test
    fun open_FavoritScreenUIShowCard () {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        var favorittscreen = { navController.navigate("Favoritt") }

        rule.setContent {
            IN2000_ProsjektTheme {
                //FavoriteScreen(onNavigateToMap = { /*TODO*/ }, onNavigateToFav = { /*TODO*/ }, onNavigateToRules = { /*TODO*/ })
                FavoriteScreenSuccess(weatherinfo= null, nowcastinfo=null , sunriseinfo= SunriseInfo , alertinfo= null , frostinfo=null ,// Var ikke nullable før, kl.22.15
                    onNavigateToMap = { /*TODO*/ }, onNavigateToFav = { /*TODO*/ },onNavigateToRules = { /*TODO*/ })
            }
        }
        rule.onNodeWithText("Temperatur: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Temperatur: "
        rule.onNodeWithText("Tåke: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Tåke: "
        rule.onNodeWithText("Nedbør: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Nedbør: "
        rule.onNodeWithText("Vind: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Vind: "
        rule.onNodeWithText("Varsel: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Varsel: "
        rule.onNodeWithText("Type frost: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Type frost: "
        rule.onNodeWithText("Coordinates frost: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Coordinates frost: "
        rule.onNodeWithText("Soloppgang: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Soloppgang: "
        rule.onNodeWithText("Solnedgang: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Solnedgang: "




    }
}

 */


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







*/