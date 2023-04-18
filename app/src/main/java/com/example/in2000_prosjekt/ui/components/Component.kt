package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.*
import com.example.in2000_prosjekt.ui.theme.*

@Composable
fun Sikt_BottomBar(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToRules: () -> Unit, favoritt : Color, map : Color, rules : Color) {

    BottomAppBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)),
        containerColor = Sikt_lyseblå,

        ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Column (horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(120.dp)
            ){
                IconButton(onClick = { onNavigateToMap() }) {
                    var iconfarge = Sikt_mellomblå
                    if (map == Sikt_mellomblå) {
                        iconfarge = Sikt_hvit
                    }
                    Icon(
                        Icons.Outlined.LocationOn,
                        contentDescription = "Localized description",
                        tint = iconfarge,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(map)
                            .padding(5.dp))
                }
                Text(text = "Utforsk")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(120.dp)
            ) {

                IconButton(onClick = { onNavigateToFav() }) {
                    var iconfarge = Sikt_mellomblå
                    if (favoritt == Sikt_mellomblå) {
                        iconfarge = Sikt_hvit
                    }
                    Icon(
                        Icons.Outlined.Favorite,
                        contentDescription = "Localized description",
                        tint = iconfarge,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(favoritt)
                            .padding(5.dp)
                    )
                }
                Text(text = "Favoritter")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(120.dp)
            ) {
                IconButton(onClick = { onNavigateToRules() }) {
                    var iconfarge = Sikt_mellomblå
                    if (rules == Sikt_mellomblå) {
                        iconfarge = Sikt_hvit
                    }
                    Icon(
                        Icons.Outlined.Menu,
                        "",
                        tint = iconfarge,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(rules)
                            .padding(5.dp)
                    )
                }
                Text(text = "Fjellvettreglene")
            }
        }
    }
}

@Composable
fun Sikt_BottomBar2( ) {

    BottomAppBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)),
        containerColor = Sikt_lyseblå,

        ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Column (horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(120.dp)
            ){
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Outlined.LocationOn,
                        contentDescription = "Localized description",
                        tint = Sikt_mellomblå,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Sikt_lyseblå)
                            .padding(5.dp))
                }
                Text(text = "Utforsk")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(120.dp)
            ) {
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Outlined.Favorite,
                        contentDescription = "Localized description",
                        tint = Sikt_mellomblå,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Sikt_lyseblå)
                            .padding(5.dp)
                    )
                }
                Text(text = "Favoritter")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(120.dp)
            ) {
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Outlined.Menu,
                        "",
                        tint = Sikt_mellomblå,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Sikt_lyseblå)
                            .padding(5.dp)
                    )
                }
                Text(text = "Fjellvettreglene")
            }
        }
    }
}


@Composable
fun Sikt_favoritt_tekst() {
    //CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Sikt_lyseblå), title = {
    Text(
        text = "Favoritter",
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.
        padding(10.dp)
    )
}


@Composable
fun Sikt_sol() {
    Image(
        painter = painterResource(id = R.drawable.sol),
        contentDescription = "sol",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sikt_favoritter_card(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Sikt_lyseblå),
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(Color(0xFFCDDCEB)) // vil sette bagrunnsfargen til sikt_lyseblå men ????
    ) {
        //todoooo
        Text(text = "Hei hei sikt")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sikt_Card() {
    Card(
        colors = CardDefaults.cardColors(Sikt_lyseblå),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Galdhøpiggen", fontWeight = FontWeight.Bold, fontSize = 30.sp, textAlign = TextAlign.Center)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToppCard(weatherinfo: LocationInfo, nowcastinfo: NowCastInfo, sunriseinfo: SunriseInfo,
    alertinfo: MutableList<AlertInfo>
             //, frostinfo: FrostInfo
) {
    val varsel = "0" //midlertidlig, egt metAlert som skal brukes
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .background(
                Sikt_lyseblå
            )
            .fillMaxWidth()) {
            //Fare Ikon på linje med navnet
            Row(
                modifier = Modifier
                .padding(20.dp)
            ){
                //Alert pop up dialog
                var openDialog by remember {
                    mutableStateOf(false)
                }

                if (alertinfo.size != 0){

                    AlertButton(alertinfo[0].alertTypeA, alertinfo[0].alertLevelA){
                        openDialog = true
                    }
                }

                if (openDialog){
                    AlertDialog(alertinfo = alertinfo){
                        openDialog = false
                    }
                }

                Spacer(modifier = Modifier
                    .height(20.dp))
                // MÅ ENDRE TIL TOPPNAVN
                Text(text = "TOPPNAVN", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }


            Spacer(modifier = Modifier
                .height(20.dp))

            Text(text = "Temperatur: ${nowcastinfo.temperatureNow}", fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier
                .height(20.dp))
            Text(text = "Tåke: ${weatherinfo.fog_area_fractionL}", fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier
                .height(20.dp))
            Text(text = "Nedbør: ${weatherinfo.rainL}", fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier
                .height(20.dp))
            Text(text = "Vind: ${nowcastinfo.windN}", fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier
                .height(20.dp))
            Text(text = "Varsel: $varsel", fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier
                .height(30.dp))

            /*
            Text(text = "Type frost: ${frostinfo.typeFrost}", fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier
                .height(30.dp))
            Text(text = "Coordinates frost: ${frostinfo.latFrost}, ${frostinfo.longFrost}", fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier
                .height(30.dp))
             */
            Column(

                modifier = Modifier.fillMaxWidth(),
                //horizontalArrangement = Arrangement.SpaceEvenly  endret fra row til column, ds måtte denne kommenteres ut
            ) {
                Text(text = "Soloppgang: ${sunriseinfo.sunriseS}", fontFamily = FontFamily.Monospace)
                Text(text = "Solnedgang: ${sunriseinfo.sunsetS}", fontFamily = FontFamily.Monospace)
            }
            Spacer(modifier = Modifier
                .height(50.dp))
        }
    }
}

@SuppressLint("DiscouragedApi")
@Composable
fun AlertButton(alertType : String, alertLevel : String, onButtonClick: () -> Unit){
    val typebind = alertType.split("; ")
    val type = typebind[1].split("-")
    val level = alertLevel.split("; ")

    val buttonimage = "${type[0]}_${level[1]}"
    Log.d("ALERT: ", buttonimage)

    val context = LocalContext.current.applicationContext
    val id = context.resources.getIdentifier(buttonimage, "drawable", context.packageName)


    Image(modifier = Modifier.clickable { onButtonClick() },
        //hardkodet inn snow_yellow for test
        painter = painterResource(id = id),
        contentDescription = "alert",
        alignment = Alignment.TopStart)
}

@Composable
fun AlertDialog(alertinfo: MutableList<AlertInfo>, onDismiss: () -> Unit){

    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column{
                alertinfo.forEach {
                    Alert_Card(alert = it)
                }
            }
        }
    }
}

@Composable
fun Sikt_BlueButton(title : String) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(Sikt_lyseblå),
        modifier = Modifier.width(172.dp)
    ) {
        Text(text = title, color = Sikt_mellomblå, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun Sikt_GreyButton(title : String) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(Sikt_grå),
        modifier = Modifier.width(172.dp)
    ) {
        Text(text = title, color = Sikt_sort, fontWeight = FontWeight.Bold)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Alert_Card(alert: AlertInfo){

    //scrollstate for verticalScroll
    val scrollState = rememberScrollState()

    val typebind = alert.alertTypeA.split("; ")
    val type = typebind[1].split("-")
    val level = alert.alertLevelA.split("; ")

    //farevarselikon
    val buttonimage = "${type[0]}_${level[1]}"
    val context = LocalContext.current.applicationContext
    val id = context.resources.getIdentifier(buttonimage, "drawable", context.packageName)

    //level er 1,2,3,4 eller 5. Definerer hvilket fare bilde vi skal ha, eller skal vi ta det på level[1] som gir farge?
    val alertLevel = level[0]
    Card(
      modifier = Modifier
          .fillMaxWidth()
          //gjør cardet scrollable
          .verticalScroll(scrollState)
    ){
        Column(
            //Spacer
            modifier = Modifier
                .padding(20.dp)
        ){
            //Farevarsel ikon
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = id),
                    contentDescription = "alert",
                    alignment = Alignment.TopStart
                )
                Text(text = alert.areaA, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier
                .height(10.dp))

            //Alert Melding8
            Text(text = alert.typeA, fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier
                .height(10.dp))

            val beskrivelselist = alert.descriptionA.split(": ")
            Text(text = "Beskrivelse: \n"+ beskrivelselist[1], fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier
                .height(10.dp))
            Text(text = "Konsekvens: \n" + alert.consequenseA, fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier
                .height(10.dp))
            Text(text = "Anbefaling: \n" + alert.recomendationA, fontFamily = FontFamily.Monospace)

            Spacer(modifier = Modifier
                .height(10.dp))

            if(alert.timeIntervalA != null){

                val starttime = alert.timeIntervalA[0]?.split("T")
                val endtime = alert.timeIntervalA[1]?.split("T")
                val startid = starttime?.get(1)?.split(":")
                val start = "${startid?.get(0)}:${startid?.get(1)}"
                val endtid = endtime?.get(1)?.split(":")
                val end = "${endtid?.get(0)}:${endtid?.get(1)}"

                Text(text = "Tidsperiode ", fontFamily = FontFamily.Monospace)
                //Skal vi endre dato format? står nå på YYYY-MM-DD, uoversiktlig å lese?
                Text(text = "Fra: ${starttime?.get(0)} - $start", fontFamily = FontFamily.Monospace)
                Text(text = "Til: ${endtime?.get(0)} - $end", fontFamily = FontFamily.Monospace)

            }

            Spacer(modifier = Modifier
                .height(10.dp))

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
                //fontweight1 = FontWeight.Bold
            } else if (alertLevel == "2"){
                highlight2 = Color.Black
                //fontweight2 = FontWeight.Bold
            } else if (alertLevel == "3"){
                highlight3 = Color.Black
                //fontweight3 = FontWeight.Bold
            } else if (alertLevel == "4"){
                highlight4 = Color.Black
                //fontweight4 = FontWeight.Bold
            } else if (alertLevel == "5"){
                highlight5 = Color.Black
                //fontweight5 = FontWeight.Bold
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
                Text(text = "Faregrad 1 - liten fare", fontFamily = FontFamily.Monospace,
                    color= highlight1,
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
                Text(text = "Faregrad 2 - liten fare", fontFamily = FontFamily.Monospace,
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
                Text(text = "Faregrad 3 - liten fare", fontFamily = FontFamily.Monospace,
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
                Text(text = "Faregrad 4 - liten fare", fontFamily = FontFamily.Monospace,
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
                    contentScale = ContentScale.FillWidth                )
                Text(text = "Faregrad 5 - liten fare", fontFamily = FontFamily.Monospace,
                    color = highlight5,
                    fontWeight = fontweight5
                )
            }



            //level, type, area, consequenses, instruction
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun TestComponent() {
}
