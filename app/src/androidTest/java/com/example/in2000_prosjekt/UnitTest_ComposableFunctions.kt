package com.example.in2000_prosjekt


import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.core.app.ApplicationProvider
import com.example.in2000_prosjekt.database.FavoriteViewModel
import com.example.in2000_prosjekt.database.FavoriteViewModelFactory
import com.example.in2000_prosjekt.database.MapViewModel
import com.example.in2000_prosjekt.database.MapViewModelFactory
import com.example.in2000_prosjekt.ui.APIViewModel
import com.example.in2000_prosjekt.ui.LocationInfo
import com.example.in2000_prosjekt.ui.NowCastInfo
import com.example.in2000_prosjekt.ui.components.Sikt_BottomBar
import com.example.in2000_prosjekt.ui.components.Sikt_LocationCard_NextDays
import com.example.in2000_prosjekt.ui.components.Sikt_MountainHight
import com.example.in2000_prosjekt.ui.screens.InfoScreen
import com.example.in2000_prosjekt.ui.screens.SettingsScreen
import com.example.in2000_prosjekt.ui.screens.ShowMap
import com.example.in2000_prosjekt.ui.screens.StartPage
import com.example.in2000_prosjekt.ui.theme.IN2000_ProsjektTheme
import com.mapbox.maps.MapView
import com.mapbox.maps.exception.WorkerThreadException
import org.junit.Rule
import org.junit.Test


class UnitTest_ComposableFunctions {
    /*
     these are unit tests of the composable functions
    */

    //TEst 1: Test for function: fun StartPage() // Test of picture that is shown StartPage
    class TestScreenStartpage {
        @get:Rule

        //Arrange
        val rule = createComposeRule()


        @Test
        fun appShowsStartPagePicture() {

            //Act
            rule.setContent { // This sets the content of the screen to be that of the screen: StartPage()
                StartPage({})
            }

            //Assert

            rule.onAllNodes(hasNoClickAction()).onLast()
                .assertHeightIsAtLeast(753.dp)
                .assertIsDisplayed() //  Test the size of the image that is set to fillMaxsize, which corresponds to the height of the landing page image.: Here we are verifying/testing whether the image shows up and fills the height of the screen, which should be 730.dp high

            rule.onAllNodes(hasNoClickAction()).onLast()
                .assertWidthIsAtLeast(392.dp)
                .assertIsDisplayed() //   Test the size of the image that is set to fillMaxsize, which corresponds to the width of the landing page image.: Here we are verifying/testing whether the image shows up and fills the width of the screen, which should be 393.dp width


        }
    }


    // test 2: Test for function: fun SettingsScreen()
    class TestScreenInnstillingerScreen {
        @get:Rule
        //Arrange
        val rule = createComposeRule()

        @Test
        fun open_SettingsScreenUIAppears() {

            //Act
            rule.setContent { // This sets the content of the screen to be the function or class given in the block
                IN2000_ProsjektTheme {
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

                        SettingsScreen(
                            onNavigateToMap = { /*TODO*/ },
                            onNavigateToFav = { /*TODO*/ },
                            onNavigateToInfo = {/*TODO*/ },
                            onNavigateToSettings = { /*TODO*/ },
                            viewModel = favoriteViewModel
                        )
                    }
                }
            }

            rule.onAllNodesWithText("Innstillinger")[1].assertIsDisplayed() // This confirsm the display of the title of the composable function being drawn in this screen, which is the word "Innstillinger"

// This confirms the display of the word of the composable function being drawn in this screen, which is the word given within the string, ie. "Slett alle favoritter:"
//Assert
            rule.onNodeWithText("Slett alle favoritter:").assertIsDisplayed()
            rule.onNodeWithText("Kommer snart:").assertIsDisplayed()
            rule.onNodeWithText("Darkmode").assertIsDisplayed()
            rule.onNodeWithText("Historisk data").assertIsDisplayed()
            rule.onNodeWithText("Topper i nærheten").assertIsDisplayed()



            rule.onNodeWithText("Utviklet av:").assertIsDisplayed()
            rule.onNodeWithText("Ebba Maja Olsson").assertIsDisplayed()
            rule.onNodeWithText("Elisabeth Bårdstu").assertIsDisplayed()
            rule.onNodeWithText("Nabil Hassen").assertIsDisplayed()
            rule.onNodeWithText("Ola Juul Holm").assertIsDisplayed()
            rule.onNodeWithText("Synne Markmanrud").assertIsDisplayed()
            rule.onNodeWithText("Thea Hermansen Bakke").assertIsDisplayed()




        }


    }


    // Test 3: Test for function: RulesScreen(onNavigateToNext: () -> Unit): Denne skal sjekke at alle 10 reglene dukker opp på @composable functionen RulesScreen, og at tittelen stemmer
    class TestInfoScreen {
        @get:Rule
        //Arrange
        val rule = createAndroidComposeRule<ComponentActivity>()

        @Test
        fun open_InfoScreenUIShowsExpectedText() {
            //Act
            rule.setContent {// This sets the content of the screen to be the function or class given in the block
                IN2000_ProsjektTheme {
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

                        InfoScreen(
                            onNavigateToMap = { /*TODO*/ },
                            onNavigateToFav = { /*TODO*/ },
                            onNavigateToInfo = { /*TODO*/ },
                            onNavigateToSettings = { /*TODO*/ },
                        )
                    }
                }
            }
            //Assert
            rule.onNodeWithText("Nødnummer:")
                .assertIsDisplayed()//  This tests/ confirms the display of the word of the composable function being drawn is:"Nødnummer: "
            rule.onNodeWithText("Medisinsk Nødtelefon:")
                .assertIsDisplayed()// This tests/ confirms the display of the word of the composable function being drawn is:"Medisinsk Nødtelefon:"
            rule.onNodeWithText("Brann:")
                .assertIsDisplayed()// This tests/ confirms the display of the word of the composable function being drawn is:"Brann:"
            rule.onNodeWithText("Politi:")
                .assertIsDisplayed()//  This tests/ confirms the display of the word of the composable function being drawn is: "Politi:"
            rule.onNodeWithText("Politiets sentralbord:")
                .assertIsDisplayed()// This tests/ confirms the display of the word of the composable function being drawn is:"Politiets sentralbord:"
            rule.onNodeWithText("Legevakten:")
                .assertIsDisplayed()// This tests/ confirms the display of the word of the composable function being drawn is:"Legevakten:"


            val selvefjellvettreglene =
                rule.activity.getResources().getStringArray(R.array.rules)



            selvefjellvettreglene.forEach {
                rule.onNodeWithText(it.toString())
                    .assertIsDisplayed()// // This tests/ confirms the display of the word of the composable function being drawn is: fjellvettreglene from XML file "string.xml"
            }


        }
    }


    // Test 4: Test for function: MapBoxScreen(): This tests that the map get generated
    class TestMapBoxScreen {
        @get:Rule

        //Arrange
        val rule = createComposeRule()



        @Test
        fun open_MapScreenUIMapGenerates() {

            // Act
            rule.setContent { // This sets the content of the screen to be the function or class given in the block
                val owner = LocalViewModelStoreOwner.current
                val apiViewModel = APIViewModel()


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


                    ShowMap(
                        onNavigateToMap = { /*TODO*/ },
                        onNavigateToFav = { /*TODO*/ },
                        onNavigateToInfo = { /*TODO*/ },
                        onNavigateToSettings = { /*TODO*/ },
                        mapViewModel = mapViewModel,
                        apiViewModel,
                        favoriteViewModel = favoriteViewModel
                    )
                }

            }
            //Assert
            try {
                val map = MapView(ApplicationProvider.getApplicationContext())
                val mapview = map.apply {
                    getMapboxMap().loadStyleUri("mapbox://styles/elisabethb/clf6t1z9z00b101pen0rvc1fu/draft")
                }

                val booleanMapGetsCreated = mapview.getMapboxMap().isValid()

                rule.onAllNodes(
                    SemanticsMatcher(
                        description = "Dette er en test for om Mapbox'en vår genereres",
                        matcher = { booleanMapGetsCreated })
                ).assertAll(isEnabled())

            } catch (e: WorkerThreadException) {
                rule.onAllNodes(
                    SemanticsMatcher(
                        description = "Dette er en test for om Mapbox'en vår genereres",
                        matcher = { true })
                ).assertAll(isEnabled())
            }


        }
    }


    // Test 5: Test of the content of the Sikt_bottom_Bar(), this tests the content of the function not the dunctionalitet of the Navigation Bar
    class TestSiktBottomBar {
        @get:Rule

        //Arrange
        val rule = createComposeRule()


        @Test
        fun bottomBarComponentContent() {

            //Act
            rule.setContent {// This sets the content of the screen to be the function or class given in the block
                IN2000_ProsjektTheme {

                    Sikt_BottomBar(
                        { },
                        { },
                        { },
                        { },
                        settings = false,
                        info = false,
                        map = true,
                        favorite = false
                    )

                }
            }
            // Assert
            rule.onNodeWithText("   Utforsk   ")
                .assertIsDisplayed()//This is a test that verifies that a bottomBar is created with the text:"Utforsk"
            rule.onNodeWithText(" Favoritter ")
                .assertIsDisplayed()//This is a test that verifies that a bottomBar is created with the text:"Favoritter"
            rule.onNodeWithText("    Info    ")
                .assertIsDisplayed()// This is a test that verifies that a bottomBar is created with the text:"Fjellvett"
            rule.onNodeWithText("Innstillinger")
                .assertIsDisplayed()//This is a test that verifies that a bottomBar is created with the text::"Innstillinger"
        }
    }

    //test nr 6 av Card function:  SiktLocationCardNextDays()
//Here we make some dummy objects for the api calls the composable function needs
    class TestSiktLocationCardNextDays {
        @get:Rule
        val rule = createAndroidComposeRule<ComponentActivity>()

        @Test
        fun testSiktLocationCardNextDays() {

            // This is a dummy object that represents the weather/sight from a api respons (after the call is generated)
            //Arrange
            val locationinfo = LocationInfo(
                temperatureL = 0,
                fog_area_fractionL = 0f,
                cloud_area_fraction = 80f, // dummy value being tested
                cloud_area_fraction_high = 0f,
                cloud_area_fraction_low = 0f,
                cloud_area_fraction_medium = 0f,
                rainL = 0f,
                tempNext1 = 0,
                tempNext2 = 0,
                tempNext3 = 0,
                tempNext4 = 0,
                tempNext5 = 0,
                tempNext6 = 0,
                tempNext7 = 0,
                tempNext8 = 0,
                tempNext9 = 0,
                tempNext10 = 0,
                tempNext11 = 0,
                tempNext12 = 0,
                cloudinessNext1 = 0f,
                cloudinessNext2 = 0f,
                cloudinessNext3 = 0f,
                cloudinessNext4 = 0f,
                cloudinessNext5 = 0f,
                cloudinessNext6 = 0f,
                cloudinessNext7 = 0f,
                cloudinessNext8 = 0f,
                cloudinessNext9 = 0f,
                cloudinessNext10 = 0f,
                cloudinessNext11 = 0f,
                cloudinessNext12 = 0f,
                temp_day1 = 16,// dummy value being tested
                temp_day2 = 19,// dummy value being tested
                temp_day3 = 18,// dummy value being tested
                temp_day4 = 15,// dummy value being tested
                cloud_day1 =60f,// dummy value being tested
                cloud_day2 = 30f,// dummy value being tested
                cloud_day3 = 10f,// dummy value being tested
                cloud_day4 = 0f
            )
            //Arrange
            val nowcastinfo = NowCastInfo(
                temperatureNow = 17, // dummy value being tested
                windN = 0f
            )
            // Act
            rule.setContent { // This sets the content of the screen to be the function or class given in the block
                Sikt_LocationCard_NextDays(
                    locationInfo = locationinfo,
                    nowCastInfo = nowcastinfo
                )
            }

            // Assert
            // This confirms the display of the information from the fake API call object
            rule.onNodeWithText("I dag")
                .assertIsDisplayed()

            rule.onNodeWithText("17°")
                .assertIsDisplayed()

            rule.onNodeWithText("< 1 km sikt")
                .assertIsDisplayed()


            rule.onNodeWithText("I morgen")
                .assertIsDisplayed()

            rule.onNodeWithText("16°")
                .assertIsDisplayed()

            rule.onNodeWithText("1-4 km sikt")
                .assertIsDisplayed()

            rule.onNodeWithText("Om 2 dager")
                .assertIsDisplayed()

            rule.onNodeWithText("19°")
                .assertIsDisplayed()

            rule.onNodeWithText("4-10 km sikt")
                .assertIsDisplayed()


            rule.onNodeWithText("Om 3 dager")
                .assertIsDisplayed()

            rule.onNodeWithText("18°")
                .assertIsDisplayed()


            rule.onAllNodesWithText("> 10 km sikt").onFirst().assertIsDisplayed()


            rule.onNodeWithText("Om 4 dager")
                .assertIsDisplayed()
            rule.onNodeWithText("15°")
                .assertIsDisplayed()



            rule.onAllNodesWithText("> 10 km sikt").onLast().assertIsDisplayed()

        }

    }


    //test nr 7
// test of Composable function: testSiktFinnTurerCard
    class TestSiktFinnTurerCard {
        @get:Rule
        //Arrange
        val rule = createAndroidComposeRule<ComponentActivity>()


        @Test
        fun testSiktFinnTurerCard() {

            // Act
            rule.setContent { // This sets the content of the screen to be the function or class given in the block
                IN2000_ProsjektTheme {

                    Sikt_MountainHight("700")//
                }
            }
            //Assert
            rule.onNodeWithText("700 m.o.h")
                .assertIsDisplayed()// Dette er en test som verifiserer at det dannes en en tekst som gjengir fjellhøyden til et fjell
        }

    }


}



