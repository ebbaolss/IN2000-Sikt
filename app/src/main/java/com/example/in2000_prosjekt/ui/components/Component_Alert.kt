package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.AlertInfo
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå

@SuppressLint("DiscouragedApi")
@Composable
fun AlertButton(alertType : String, alertLevel : String, onButtonClick: () -> Unit){
    val typebind = alertType.split("; ")
    val type = typebind[1].split("-")
    val level = alertLevel.split("; ")

    val buttonimage = "${type[0]}_${level[1]}"
    Log.d("ALERT: ", buttonimage)

    val context = LocalContext.current.applicationContext
    val id = context.resources.getIdentifier(buttonimage.lowercase(), "drawable", context.packageName)


    Image(modifier = Modifier.clickable { onButtonClick() },
        //hardkodet inn snow_yellow for test
        painter = painterResource(id = id),
        contentDescription = "alert",
        alignment = Alignment.TopEnd)
}

@Composable
fun AlertDialog(alertinfo: MutableList<AlertInfo>, onDismiss: () -> Unit){

    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Alert_Card(alertinfo)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun LazyListScope.Alert_Card(alertinfo: MutableList<AlertInfo>){
    items(alertinfo.size) {
        alertinfo.forEach { alert ->

            val typebind = alert.alertTypeA.split("; ")
            val type = typebind[1].split("-")
            val level = alert.alertLevelA.split("; ")

            //farevarselikon
            val buttonimage = "${type[0]}_${level[1]}"
            val context = LocalContext.current.applicationContext
            val id = context.resources.getIdentifier(
                buttonimage.lowercase(),
                "drawable",
                context.packageName
            )

            //level er 1,2,3,4 eller 5. Definerer hvilket fare bilde vi skal ha, eller skal vi ta det på level[1] som gir farge?
            val alertLevel = level[0]

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                backgroundColor = Sikt_lyseblå
            ) {
                Column(
                    //Spacer
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    //Farevarsel ikon
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Image(
                            modifier = Modifier.weight(1f),
                            contentScale = ContentScale.Fit,
                            painter = painterResource(id = id),
                            contentDescription = "alert",
                            alignment = Alignment.TopStart
                        )
                        Text(
                            modifier = Modifier
                                .wrapContentSize(align = Alignment.Center)
                                .weight(4f),
                            text = alert.areaA,
                            //Prøver å resize til å passe på en linje
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
                            )
                        )

                    }

                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                    )

                    //Alert Melding8
                    Text(
                        text = alert.typeA, fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )

                    val beskrivelselist = alert.descriptionA.split(": ")
                    Text(
                        text = "Beskrivelse: \n" + beskrivelselist[1],
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )
                    Text(
                        text = "Konsekvens: \n" + alert.consequenseA,
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )
                    Text(
                        text = "Anbefaling: \n" + alert.recomendationA,
                        fontFamily = FontFamily.Monospace
                    )

                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )

                    if (alert.timeIntervalA != null) {

                        val starttime = alert.timeIntervalA[0]?.split("T")
                        val endtime = alert.timeIntervalA[1]?.split("T")
                        val startid = starttime?.get(1)?.split(":")
                        val start = "${startid?.get(0)}:${startid?.get(1)}"
                        val endtid = endtime?.get(1)?.split(":")
                        val end = "${endtid?.get(0)}:${endtid?.get(1)}"

                        Text(text = "Tidsperiode ", fontFamily = FontFamily.Monospace)
                        //Skal vi endre dato format? står nå på YYYY-MM-DD, uoversiktlig å lese?
                        Text(
                            text = "Fra: ${starttime?.get(0)} - $start",
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = "Til: ${endtime?.get(0)} - $end",
                            fontFamily = FontFamily.Monospace
                        )

                    }

                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )

                    //For å endre farge og boldness på det der faren er
                    var highlight1 = Color.Gray
                    var highlight2 = Color.Gray
                    var highlight3 = Color.Gray
                    var highlight4 = Color.Gray
                    var highlight5 = Color.Gray
                    var fontweight1 = FontWeight.Normal
                    var fontweight2 = FontWeight.Normal
                    var fontweight3 = FontWeight.Normal
                    var fontweight4 = FontWeight.Normal
                    var fontweight5 = FontWeight.Normal

                    //kommentert ut at de blir bold intill vi blir enige om hva vi liker best.
                    if (alertLevel == "1") {
                        highlight1 = Color.Black
                        fontweight1 = FontWeight.Bold
                    } else if (alertLevel == "2") {
                        highlight2 = Color.Black
                        fontweight2 = FontWeight.Bold
                    } else if (alertLevel == "3") {
                        highlight3 = Color.Black
                        fontweight3 = FontWeight.Bold
                    } else if (alertLevel == "4") {
                        highlight4 = Color.Black
                        fontweight4 = FontWeight.Bold
                    } else if (alertLevel == "5") {
                        highlight5 = Color.Black
                        fontweight5 = FontWeight.Bold
                    }

                    Text(text = "Faregrader ", fontFamily = FontFamily.Monospace)
                    Row(
                        modifier = Modifier
                            .padding()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.green),
                            contentDescription = "green",
                            modifier = Modifier
                                .size(25.dp)
                                .padding(5.dp),
                            contentScale = ContentScale.FillWidth
                        )
                        Text(
                            text = "Faregrad 1 - liten fare", fontFamily = FontFamily.Monospace,
                            color = highlight1,
                            fontWeight = fontweight1
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.yellow),
                            contentDescription = "yellow",
                            modifier = Modifier
                                .size(25.dp)
                                .padding(5.dp),
                            contentScale = ContentScale.FillWidth
                        )
                        Text(
                            text = "Faregrad 2 - liten fare", fontFamily = FontFamily.Monospace,
                            color = highlight2,
                            fontWeight = fontweight2
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.orange),
                            contentDescription = "orange",
                            modifier = Modifier
                                .size(25.dp)
                                .padding(5.dp),
                            contentScale = ContentScale.FillWidth
                        )
                        Text(
                            text = "Faregrad 3 - liten fare", fontFamily = FontFamily.Monospace,
                            color = highlight3,
                            fontWeight = fontweight3
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.red),
                            contentDescription = "green",
                            modifier = Modifier
                                .size(25.dp)
                                .padding(5.dp),
                            contentScale = ContentScale.FillWidth
                        )
                        Text(
                            text = "Faregrad 4 - liten fare", fontFamily = FontFamily.Monospace,
                            color = highlight4,
                            fontWeight = fontweight4
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.dark_red),
                            contentDescription = "green",
                            modifier = Modifier
                                .size(25.dp)
                                .padding(5.dp),
                            contentScale = ContentScale.FillWidth
                        )
                        Text(
                            text = "Faregrad 5 - liten fare", fontFamily = FontFamily.Monospace,
                            color = highlight5,
                            fontWeight = fontweight5
                        )
                    }

                    //level, type, area, consequenses, instruction
                }
            }
        }
    }
}