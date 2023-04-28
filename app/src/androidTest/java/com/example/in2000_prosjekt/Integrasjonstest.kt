package com.example.in2000_prosjekt

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
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
import org.junit.Rule
import org.junit.Test



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