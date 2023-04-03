package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.core.content.ContextCompat
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.AlertInfo
import com.example.in2000_prosjekt.ui.LocationInfo
import com.example.in2000_prosjekt.ui.NowCastInfo
import com.example.in2000_prosjekt.ui.SunriseInfo
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
    CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Sikt_lyseblå), title = {
        Text(text = "Favoritter", fontSize = 40.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
    })
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
            Text(text = "Galdhøpiggen", fontSize = 30.sp, fontWeight = FontWeight.Bold)

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
        alignment = Alignment.TopEnd)
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
    Card(
      modifier = Modifier.fillMaxWidth()
    ){
        Column(
            //Spacer
            modifier = Modifier
                .padding(20.dp)
        ){
            //Her skal det stå hvilket sted, placeholder nå
            Text(text = "Sted: " + alert.areaA , fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(text = "Type: " + alert.typeA, fontFamily = FontFamily.Monospace)
            Text(text = "Beskrivelse: "+ alert.descriptionA, fontFamily = FontFamily.Monospace)
            Text(text = "Konsekvens: " + alert.consequenseA, fontFamily = FontFamily.Monospace)
            Text(text = "Anbefaling: " + alert.recomendationA, fontFamily = FontFamily.Monospace)


        //level, type, area, consequenses, instruction
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun testComponent() {
}