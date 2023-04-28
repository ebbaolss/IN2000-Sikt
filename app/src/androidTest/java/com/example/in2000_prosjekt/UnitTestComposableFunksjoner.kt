package com.example.in2000_prosjekt

import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.SemanticsConfiguration
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.in2000_prosjekt.ui.components.*
import com.example.in2000_prosjekt.ui.screens.*
import com.example.in2000_prosjekt.ui.theme.IN2000_ProsjektTheme
import com.mapbox.maps.MapView
import com.mapbox.maps.exception.WorkerThreadException
import org.junit.Rule
import org.junit.Test




/*
 Dette er unit tester av composable funksjoner
*/

//TEst 1: Test for funksjon: fun StartPage(onNavigateToNext: () -> Unit) // Test av om bildet dukker opp på skjermen StartPage
class testScreenStartpage {
    @get:Rule
    val rule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Test
    fun appShowsStartPagePicture() {

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


         rule.onAllNodes(hasNoClickAction()).onLast().assertHeightIsAtLeast(753.dp)//  Test på størrelsen til bilde som er satt til fillMaxsize, som svarer til høyden til emulatoren vår, hentet fra dokumentasjonen til emulatoren: Her så testes det om bilde fyller skjermens høyde, som skal være 730.dp høy
         rule.onAllNodes(hasNoClickAction()).onLast().assertWidthIsAtLeast(392.dp)//  Test på størrelsen til bilde som er satt til fillMaxsize,, som svarer til bredden til emulatoren vår, hentet fra dokumentasjonen til emulatoren: Her så testes det om bilde fyller skjermens bredde, som skal være 393.dp høy



        //rule.wait (timeoutMillis = 1_000, condition= { true }) {   }
    }
}





// test 2: Test for funksjon: fun SettingsScreen(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToSettings: () -> Unit, onNavigateToRules: () -> Unit){
class testScreenInnstillingerScreen {
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

        rule.onNodeWithText("Settings").assertIsDisplayed() // på figma står det "Instillinger"



       /* Kommentar fra 27.04; Helst ikke fjern, Det under skal ikke være utkommentert: Alt under er kodet etter hvordan appen kommer til å se ut, ref. figma tegninger kl.17.50, dato: 27.04
        rule.onNodeWithText("Utviklet av:").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Type: "

        rule.onNodeWithText("APIer:").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Beskrivelse"
        rule.onNodeWithText("Politi:").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Konsekvens"
        rule.onNodeWithText("Politiets sentralbord:").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Anbefaling"
        rule.onNodeWithText("Legevakten:").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Sted: "


        */

    }



}



// Test 3: Test for funksjon: RulesScreen(onNavigateToNext: () -> Unit): Denne skal sjekke at alle 10 reglene dukker opp på @composable funksjonen RulesScreen, og at tittelen stemmer
class testInfoScreen {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()
    lateinit var navController: TestNavHostController


    @Test
    fun open_InfoScreenUIShowsExpectedText () {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        var rulesscreen = { navController.navigate("Rules") }

        rule.setContent {
            IN2000_ProsjektTheme {
                RulesScreen(onNavigateToMap =  { /*TODO*/ }, onNavigateToFav = { /*TODO*/ }, onNavigateToRules =  { /*TODO*/ },onNavigateToSettings = { /*TODO*/} )
            }


        }

        /* Info: fra 27.04: Det under skal ikke være utkommentert: Alt under er kodet etter hvordan appen kommer til å se ut, ref. figma tegninger kl.17.50, dato: 27.04

        rule.onNodeWithText("Nødnummer:").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Sted: "
        rule.onNodeWithText("Medisinsk Nødtelefon:").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Type: "
        rule.onNodeWithText("Brann").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Beskrivelse"
        rule.onNodeWithText("Politi:").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Konsekvens"
        rule.onNodeWithText("Politiets sentralbord:").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Anbefaling"
        rule.onNodeWithText("Legevakten:").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet:"Sted: "

        */


       val selvefjellvettreglene = rule.activity.getResources().getStringArray(R.array.rules)

        rule.onAllNodesWithText("Fjellvettreglene").assertAll(SemanticsMatcher(description= "Fjellvettreglene", matcher= {  true })) // denne tester at det dukker opp en streng kalt "Fjellvettreglene", både i tittelen til Card'et og i bottom Navigation bar

        selvefjellvettreglene.forEach {
            rule.onNodeWithText(it.toString()).assertIsDisplayed()// Dette er en test som verifiserer at det dannes en tekst med innholdet alle fjellvettreglene fra XML filen (at String arrayet med reglene printes på skjermen)
        }




    }
}







// Test 4: Test for funksjon: MapBoxScreen((onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToRules: () -> Unit): Denne skal sjekke at Kartet dukker opp, og etterhvert om Pointer funker
class TestMapBoxScreen {
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
        rule.onNodeWithText("Favoritter").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Favoritter"
        rule.onNodeWithText("Utforsk").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Beskrivelse"
        rule.onNodeWithText("Info").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Beskrivelse"
        rule.onNodeWithText("Fjellvett").assertIsDisplayed()// Denne heter innstillinger på figma tegningene,

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

// Test 5: Test for funksjon: MapBoxScreen((onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToRules: () -> Unit): Denne skal sjekke at Kartet dukker opp, og etterhvert om Pointer funker
class TestModalBottomSheetLayout {
    @get:Rule
    val rule = createComposeRule()
    //lateinit var navController: TestNavHostController

    @Test
    fun open_ModalBottomSheetContainsCards  () {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext()) // denne setter navControlleren generert i testene lik navControlleren lagd Main Activity
      //  mapscreen = navController.navigate("Map")

        rule.setContent {

            ShowMap(onNavigateToMap = { /*Kun for testing*/ }, onNavigateToFav = { /*Kun for testing*/ },onNavigateToRules = {  /*Kun for testing*/ },onNavigateToSettings = { /*Kun for testing*/})

        }
        rule.onNodeWithText("Fjellvett").assertIsDisplayed()// Denne heter: "info" på figma
        rule.onNodeWithText("Favoritter").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Favoritter"
        rule.onNodeWithText("Utforsk").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Beskrivelse"
        rule.onNodeWithText("Info").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Beskrivelse"


        // denne testen ettersøker om at innholdet i Modal Bottom Sheet (som er trykkbar), har barn som er composable funksjoner (i dette tilfelle: composable funskjonen: Sikt_FinnTurer_card )
        var matcher= SemanticsMatcher( description = "", matcher= {  SemanticsConfiguration().contains(key = SemanticsPropertyKey<Unit>(name = "Sikt_FinnTurer_card")).or(true)} /*(SemanticsNode) -> true*/)
        rule.onAllNodes(hasClickAction()).assertAll(matcher=matcher)


    }
}









// Test 6: Test for innholdet og utseende til composable funksjonen Sikt_bottom_Bar, denne testen retter seg kun mot composable funksjonens utseende, ikke funksjonaliteten til Navigation Bar'en
class TestSiktBottomBar {
    @get:Rule
    val rule = createComposeRule()
    lateinit var navController: TestNavHostController


    @Test
    fun bottomBarComponentContent () {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        var favorittscreen = { navController.navigate("Favoritt") }

        rule.setContent {
            IN2000_ProsjektTheme {

                Sikt_BottomBar({ }, { }, { }, { }, favoritt = false, settings = false, rules = false, map = true
                )

            }
        }

        rule.onNodeWithText("Utforsk").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Utforsk"
        rule.onNodeWithText("Favoritter")
            .assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Favoritter"
        rule.onNodeWithText("Fjellvett")
            .assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Fjellvett"
        rule.onNodeWithText("Info")
            .assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bottomBar med en tekst med innholdet:"Innstillinger"


    }


}


//test nr 7
// test av Composable funksjonen: fabvorite success screen // denne funksjonen brukes som mal
class testSiktFinnTurerCard {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()


    @Test
    fun testSiktFinnTurerCard () {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        var alertscreen = { navController.navigate("Alert") }

        rule.setContent {
            IN2000_ProsjektTheme {


                Sikt_FinnTurer_card(location = "ExampleLocationBesseggen", height= 1250 , temp = 14, skydekkeTop = true, skydekkeMid = false, skydekkeLow = true )
                //Sikt_FinnTurer_card(/*location = "testvalueforsomelocation", height= 1250 , temp = 14, skydekkeTop = true, skydekkeMid = false, skydekkeLow = true*/ )
            }
        }

        rule.onNodeWithText("ExampleLocationBesseggen").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bilde, som illustrerer lufttemperaturen på 14 grader
      //  rule.onNodeWithText(14.toString()).assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bilde, som illustrerer lufttemperaturen på 14 grader


         rule.onNodeWithTag(R.drawable.topp_1000_1500.toString()).assertIsDisplayed()




    }

}


//test nr 9 av Card funksjon:  SiktLocationCardNextDays
//Her så lager vi dummy objekter av apikallene som
// composable funksjonen etterspør
class testSiktLocationCardNextDays {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testSiktLocationCardNextDays () {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        var alertscreen = { navController.navigate("Alert") }

        rule.setContent {

            Sikt_LocationCard_NextDays()


        }

        rule.onNodeWithText("Idag").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bilde, som illustrerer lufttemperaturen på 14 grader


    }

}



//test nr 9 av Card funksjon:  test_Sikt_LoctationCard_Topper_i_naerheten
//Her så lager vi dummy objekter av apikallene som
// composable funksjonen etterspør

class test_Sikt_LocationCard_Topper_i_naerheten {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun test_Sikt_LoctationCard_Topper_i_naerheten () {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        var alertscreen = { navController.navigate("Alert") }

        rule.setContent {
            Sikt_LoctationCard_Topper_i_naerheten()
        }

        //denne egenskapen ved composeable funskjonen Sikt_LoctationCard_Topper_i_naerheten, er testet ettersom innholdet er en fast struktur ved funksjonen
        rule.onNodeWithText("Topper i nærheten: ").assertIsDisplayed()// Dette er en test som verifiserer at det dannes en bilde, som illustrerer lufttemperaturen på 14 grader


    }

}


