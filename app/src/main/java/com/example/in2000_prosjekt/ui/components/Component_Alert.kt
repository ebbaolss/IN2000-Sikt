package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
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
                .fillMaxSize().padding(top = 1.dp, bottom = 1.dp)
        ) {
            Alert_Card(alertinfo)
        }

    }
}


fun LazyListScope.Alert_Card(alertinfo: MutableList<AlertInfo>){

    items(alertinfo.size) {
        val alert = alertinfo[it]

            val typebind =alert.alertTypeA.split("; ")
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
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(Sikt_lyseblå)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    //Farevarsel ikon
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.weight(1f),
                            contentScale = ContentScale.Fit,
                            painter = painterResource(id = id),
                            contentDescription = "alert",
                            alignment = Alignment.TopStart
                        )
                        Text(
                            modifier = Modifier.weight(4f),
                            text = alert.areaA,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                            )
                        )
                    }

                    //Divider(thickness = 1.dp, color = Sikt_sort, modifier = Modifier.fillMaxWidth().padding(10.dp))

                    //Alert Melding8
                    Text(
                        text = alert.typeA,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    val beskrivelselist = alert.descriptionA.split(": ")

                    Column() {
                        Text(text = "Beskrivelse:", fontWeight = FontWeight.Bold)
                        Text(text = beskrivelselist[1])
                    }

                    Column() {
                        Text(text = "Konsekvens:", fontWeight = FontWeight.Bold)
                        Text(text = alert.consequenseA)
                    }

                    Column() {
                        Text(text = "Anbefaling:", fontWeight = FontWeight.Bold)
                        Text(text = alert.recomendationA)
                    }

                    if (alert.timeIntervalA != null) {

                        val starttime = alert.timeIntervalA[0]?.split("T")
                        val endtime = alert.timeIntervalA[1]?.split("T")
                        val startid = starttime?.get(1)?.split(":")
                        val start = "${startid?.get(0)}:${startid?.get(1)}"
                        val endtid = endtime?.get(1)?.split(":")
                        val end = "${endtid?.get(0)}:${endtid?.get(1)}"

                        Column() {
                            Text(text = "Tidsperiode:", fontWeight = FontWeight.Bold)
                            Text(text = "Fra: ${starttime?.get(0)} - kl: $start")
                            Text(text = "Til: ${endtime?.get(0)} - kl: $end")
                        }
                    }

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

                    Column() {
                        Text(text = "Faregrader:", fontWeight = FontWeight.Bold)
                        Row() {
                            Image(
                                painter = painterResource(R.drawable.green),
                                contentDescription = "green",
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(5.dp),
                                contentScale = ContentScale.FillWidth
                            )
                            Text(
                                text = "Faregrad 1 - liten fare",
                                color = highlight1,
                                fontWeight = fontweight1
                            )
                        }
                        Row() {
                            Image(
                                painter = painterResource(R.drawable.yellow),
                                contentDescription = "yellow",
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(5.dp),
                                contentScale = ContentScale.FillWidth
                            )
                            Text(
                                text = "Faregrad 2 - liten fare",
                                color = highlight2,
                                fontWeight = fontweight2
                            )
                        }
                        Row() {
                            Image(
                                painter = painterResource(R.drawable.orange),
                                contentDescription = "orange",
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(5.dp),
                                contentScale = ContentScale.FillWidth
                            )
                            Text(
                                text = "Faregrad 3 - liten fare",
                                color = highlight3,
                                fontWeight = fontweight3
                            )
                        }
                        Row() {
                            Image(
                                painter = painterResource(R.drawable.red),
                                contentDescription = "green",
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(5.dp),
                                contentScale = ContentScale.FillWidth
                            )
                            Text(
                                text = "Faregrad 4 - liten fare",
                                color = highlight4,
                                fontWeight = fontweight4
                            )
                        }
                        Row() {
                            Image(
                                painter = painterResource(R.drawable.dark_red),
                                contentDescription = "green",
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(5.dp),
                                contentScale = ContentScale.FillWidth
                            )
                            Text(
                                text = "Faregrad 5 - liten fare",
                                color = highlight5,
                                fontWeight = fontweight5
                            )
                        }
                    }
                    //level, type, area, consequenses, instruction
                }
            }
        }

}