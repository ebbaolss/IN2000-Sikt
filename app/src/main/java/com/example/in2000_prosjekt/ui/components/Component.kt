@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import kotlinx.coroutines.launch
import androidx.compose.material3.IconToggleButton
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun Sikt_BottomBar(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToRules: () -> Unit, onNavigateToSettings: () -> Unit, favoritt : Boolean, map : Boolean, rules : Boolean, settings : Boolean) {

    BottomAppBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)),
        containerColor = Sikt_hvit,

        ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Column (horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(120.dp)
            ){
                IconButton(onClick = { onNavigateToMap() }) {
                    var iconfarge = Sikt_mørkeblå
                    var iconBackround = Sikt_hvit
                    if (map) {
                        iconBackround = Sikt_lyseblå
                    }
                    Icon(
                        Icons.Outlined.LocationOn,
                        contentDescription = "Localized description",
                        tint = iconfarge,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(iconBackround)
                            .padding(5.dp))
                }
                Text(text = "Utforsk", fontSize = 13.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(75.dp)
            ) {

                IconButton(onClick = { onNavigateToFav() }) {
                    var iconfarge = Sikt_mørkeblå
                    var iconBackround = Sikt_hvit
                    if (favoritt) {
                        iconBackround = Sikt_lyseblå
                    }
                    Icon(
                        Icons.Outlined.Favorite,
                        contentDescription = "Localized description",
                        tint = iconfarge,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(iconBackround)
                            .padding(5.dp)
                    )
                }
                Text(text = "Favoritter", fontSize = 13.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(98.dp)
            ) {
                IconButton(onClick = { onNavigateToRules() }) {
                    var iconfarge = Sikt_mørkeblå
                    var iconBackround = Sikt_hvit
                    if (rules) {
                        iconBackround = Sikt_lyseblå
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.ny_fjellvettregler),
                        "",
                        tint = iconfarge,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(iconBackround)
                            .padding(5.dp)
                    )
                }
                Text(text = "Fjellvettreglene", fontSize = 13.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(75.dp)
            ) {
                IconButton(onClick = { onNavigateToSettings() }) {
                    var iconfarge = Sikt_mørkeblå
                    var iconBackround = Sikt_hvit
                    if (settings) {
                        iconBackround = Sikt_lyseblå
                    }
                    Icon(
                        Icons.Outlined.Settings,
                        "",
                        tint = iconfarge,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(iconBackround)
                            .padding(5.dp)
                    )
                }
                Text(text = "Innstillinger", fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun Sikt_BottomBar2( ) {
    //Denne brukes for for testing av design i preview
    BottomAppBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)),
        containerColor = Sikt_hvit,

        ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Column (horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(75.dp)
            ){
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = "Localized description",
                        tint = Sikt_mørkeblå,
                    )
                }
                Text(text = "Utforsk")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(75.dp)
            ) {
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Outlined.Favorite,
                        contentDescription = "Localized description",
                        tint = Sikt_mørkeblå,
                    )
                }
                Text(text = "Favoritter")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(93.dp)
            ) {
                IconButton(onClick = {  }) {
                    Icon(
                        painter = painterResource(id = R.drawable.fjellvettregler),
                        "",
                        tint = Sikt_mørkeblå,
                    )
                }
                Text(text = "Fjellvettreglene")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(75.dp)
            ) {
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Outlined.Settings,
                        "",
                        tint = Sikt_mørkeblå,
                    )
                }
                Text(text = "Innstillinger")
            }
        }
    }
}

@Composable
fun Sikt_Header(location : String /* ,alertType : String, alertLevel : String */) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Placeholder-ikon for advarsel:
        Icon(Icons.Outlined.Refresh, "", tint = Sikt_mørkeblå)
        //AlertButton(alertType = , alertLevel = ) {}
        Text(text = "$location", fontWeight = FontWeight.Bold, fontSize = 30.sp)
        var checked by remember { mutableStateOf(false) }
        IconToggleButton(
            checked = checked,
            onCheckedChange = { checked = it },
        ) {
            if (checked) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                    tint = Sikt_mørkeblå
                )
            } else {
                Icon(
                    painterResource(id = R.drawable.outline_favorite),
                    contentDescription = "Localized description",
                    tint = Sikt_mørkeblå
                )
            }
        }
    }
}

@Composable
fun Sikt_MountainHight(mountainheight : String) {
    Text(
        text = "$mountainheight m.o.h",
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Sikt_sol() {
    //brukes i rules
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
fun Sikt_Favorite_card( /* weatherinfo: LocationInfo, nowcastinfo: NowCastInfo, sunriseinfo: SunriseInfo, alertinfo: MutableList<AlertInfo> */) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        colors = CardDefaults.cardColors(Sikt_lyseblå)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Sikt_Header("fjelltopp" /* , alertType : String, alertLevel : String */)
            Sikt_MountainHight("1884")
            Sikt_Visualisering_and_Sikt_Info(10.5, 3.3, 0.6)
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
          .verticalScroll(scrollState),
        backgroundColor = Sikt_lyseblå
    ){
        Column(
            //Spacer
            modifier = Modifier
                .padding(10.dp)
        ){
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
                //favoritt icon button
                var checked by remember { mutableStateOf(false) }
                IconToggleButton(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1f)
                ) {
                    if (checked) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Favorite filled")
                    } else {
                        Icon(Icons.Outlined.Favorite, contentDescription = "Favorite outlined")
                    }
                }

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
                .height(20.dp))

            val beskrivelselist = alert.descriptionA.split(": ")
            Text(text = "Beskrivelse: \n"+ beskrivelselist[1], fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier
                .height(20.dp))
            Text(text = "Konsekvens: \n" + alert.consequenseA, fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier
                .height(20.dp))
            Text(text = "Anbefaling: \n" + alert.recomendationA, fontFamily = FontFamily.Monospace)

            Spacer(modifier = Modifier
                .height(20.dp))

            /*if(alert.timeIntervalA != null){

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

            }*/

            Spacer(modifier = Modifier
                .height(20.dp))

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sikt_FinnTurer_card(location : String, height : Int, temp : Int, skydekkeTop : Boolean, skydekkeMid : Boolean, skydekkeLow : Boolean ) {

    var heigthVisuals = R.drawable.topp_1000_1500

    if (height < 500) {
        heigthVisuals = R.drawable.topp__500
    } else if (height < 1000) {
        heigthVisuals = R.drawable.topp_500_1000
    } else if (height < 1500) {
        heigthVisuals = R.drawable.topp_1000_1500
    } else if (height < 2000) {
        heigthVisuals = R.drawable.topp_1500_2000
    } else if (height > 2000) {
        heigthVisuals = R.drawable.topp__2000
    }

    Card(
        colors = CardDefaults.cardColors(Sikt_lyseblå),
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$location",
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(5.dp)
            )
            Box(
                modifier = Modifier.clip(RoundedCornerShape(5.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = "",
                )
                Image(
                    painter = painterResource(id = heigthVisuals),
                    contentDescription = "",
                )
                if (skydekkeTop) {
                    Image(
                        painter = painterResource(id = R.drawable.h_yt_skydekke),
                        contentDescription = "",
                    )
                }
                if (skydekkeMid) {
                    Image(
                        painter = painterResource(id = R.drawable.middels_skydekke),
                        contentDescription = "",
                    )
                }
                if (skydekkeLow) {
                    Image(
                        painter = painterResource(id = R.drawable.lavt_skydekke),
                        contentDescription = "",
                    )
                }
                Text(
                    text = "$temp°",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Sikt_hvit,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(5.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Sikt_BottomSheet() {

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    val showModalSheet = rememberSaveable {
        mutableStateOf(false)
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = { BottomSheetContent() }
    ) {
        ModalSheetWithAnchor(sheetState, showModalSheet)
    }
}

@Composable
fun BottomSheetContent(){
    Surface(
        color = Sikt_lyseblå,
        modifier = Modifier.height(200.dp),
        // shape = RoundedCornerShape(20.dp) får ikke denne til å se bra ut :((
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Text(
                text = "Finn turer i nærheten",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 20.dp),
                color = Sikt_sort,
                fontWeight = FontWeight.Bold
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
                    .background(Sikt_lyseblå),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            // Her skal listen over topper i nærheten:
            ) {
                item {
                    Sikt_FinnTurer_card("test",2469, 3,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card("test",2469, 3,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card("test",2469, 3,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card("test",2469, 3,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card("test",2469, 3,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card("test",2469, 3,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card("test",2469, 3,true, false, true)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ModalSheetWithAnchor(sheetState: ModalBottomSheetState, showModalSheet: MutableState<Boolean>) {
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            modifier = Modifier
                .height(165.dp)
                .fillMaxWidth()
                .padding(30.dp),
            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            colors = ButtonDefaults.buttonColors(Sikt_lyseblå),
            onClick = {
                showModalSheet.value = !showModalSheet.value
                scope.launch {
                    sheetState.show()
                }
            } )
            {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    tint = Sikt_mellomblå,
                    contentDescription = "",
                    modifier = Modifier
                )
                Text(text = "Finn turer i nærheten", color = Sikt_sort, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun Sikt_Datavisualisering_Card(height : Int, temp : Float, vind : Float, skydekkeTop : Boolean, skydekkeMid : Boolean, skydekkeLow : Boolean ) {

    var heigthVisuals = R.drawable.topp_1000_1500

    if (height < 500) {
        heigthVisuals = R.drawable.topp__500
    } else if (height < 1000) {
        heigthVisuals = R.drawable.topp_500_1000
    } else if (height < 1500) {
        heigthVisuals = R.drawable.topp_1000_1500
    } else if (height < 2000) {
        heigthVisuals = R.drawable.topp_1500_2000
    } else if (height > 2000) {
        heigthVisuals = R.drawable.topp__2000
    }

    Card(
        modifier = Modifier.size(width = 170.dp, height = 220.dp),
    ) {
        Box() {

            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
            Image(
                painter = painterResource(id = heigthVisuals),
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
            if(skydekkeTop) {
                Image(
                    painter = painterResource(id = R.drawable.h_yt_skydekke),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
            if(skydekkeMid) {
                Image(
                    painter = painterResource(id = R.drawable.middels_skydekke),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
            if(skydekkeLow) {
                Image(
                    painter = painterResource(id = R.drawable.lavt_skydekke),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(text = "$temp°", fontWeight = FontWeight.Bold, fontSize = 48.sp, color = Sikt_hvit,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp))

            Box(modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)) {
                Column() {
                    Image(
                        painter = painterResource(id = R.drawable.vind_icon),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                    Text(text = "$vind m/s", fontSize = 12.sp, color = Sikt_hvit)
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Sikt_Visualisering_and_Sikt_Info(sikthigh : Double, siktmedium : Double, siktlow : Double){
        // Tar inn antall km sikt, eller tar inn %

    fun getRightSiktIcon(km: Double): Int {
        return if (km < 1) {
            R.drawable.taake_sikt
        } else if (km < 4) {
            R.drawable.daarlig_sikt
        } else if (km < 10) {
            R.drawable.moderat_sikt
        } else {
            R.drawable.god_sikt
        }
    }
    fun getRightSiktText(km: Double): String {
        return if (km < 1) {
            "Meget dårlig sikt"
        } else if (km < 4) {
            "Dårlig sikt"
        } else if (km < 10) {
            "Moderat  sikt"
        } else {
            "God sikt"
        }
    }
    
    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Sikt_Datavisualisering_Card(1800, -10f, 7f, true, false, true)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(start = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            // Hvordan space evenly uten å bli høyrere enn Sikt_Datavisualisering_Card???
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(text = "2000-5000 m.o.h")
                Text(text = getRightSiktText(sikthigh))
                Icon(painterResource(
                    id = getRightSiktIcon(sikthigh)),"",
                    modifier = Modifier.size(24.dp), tint = Sikt_mørkeblå
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(text = "1000-2000 m.o.h")
                Text(text = getRightSiktText(siktmedium))
                Icon(painterResource(
                    id = getRightSiktIcon(siktmedium)),"",
                    modifier = Modifier.size(24.dp), tint = Sikt_mørkeblå
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(text = "0-1000 m.o.h")
                Text(text = getRightSiktText(siktlow))
                Icon(
                    painterResource(
                        id = getRightSiktIcon(siktlow)
                    ), "",
                    modifier = Modifier.size(24.dp), tint = Sikt_mørkeblå
                )
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Preview(showSystemUi = true)
@Composable
fun TestComponent() {

    //Sikt_Header("test")
    //Sikt_MountainHight("test")
    //Sikt_Favorite_card()

    //BottomSheetContent()
    //Sikt_BottomSheet()

    // Denne har hardkodet str, prøver å fikse:
    //Sikt_FinnTurer_card("test", 860, -10, true, true, true)

    //Sikt_Datavisualisering_Card(860 ,-10f, 7f, false, true, true)
    //Sikt_Visualisering_and_Sikt_Info(10.5, 3.3, 0.6)
}



