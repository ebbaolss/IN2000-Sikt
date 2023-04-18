package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material.*
//import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit


@Composable
fun Sikt_BottomBar(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToRules: () -> Unit, onNavigateToSettings: () -> Unit, favoritt : Color, map : Color, settings : Color, rules : Color) {

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
                modifier = Modifier.width(75.dp)
            ) {

                IconButton(onClick = { onNavigateToFav() }) {
                    var backroundColor = Sikt_hvit
                    if (favoritt == Sikt_mellomblå) {
                        backroundColor = Sikt_lyseblå
                    }
                    Icon(
                        Icons.Outlined.Favorite,
                        contentDescription = "Localized description",
                        tint = Sikt_mørkeblå,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(backroundColor)
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
                    var backroundColor = Sikt_hvit
                    if (rules == Sikt_mellomblå) {
                        backroundColor = Sikt_lyseblå
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.ny_fjellvettregler),
                        "",
                        tint = Sikt_mørkeblå,
                        modifier = Modifier.size(120.dp)
                            .clip(CircleShape)
                            .background(backroundColor)
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
                    var backroundColor = Sikt_hvit
                    if (settings == Sikt_mellomblå) {
                        backroundColor = Sikt_lyseblå
                    }
                    Icon(
                        Icons.Outlined.Settings,
                        "",
                        tint = Sikt_mørkeblå,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(backroundColor)
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

    var multiplier by remember { mutableStateOf(1f) }
    var fontSize by remember { mutableStateOf(24.sp) } // initial font size


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
                    .padding(5.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = id),
                    contentDescription = "alert",
                    alignment = Alignment.TopStart
                )

                //Overskrift med Området Resize funker bare en gang...
                BoxWithConstraints {
                    Text(
                        modifier = Modifier
                            .wrapContentSize(align = Alignment.Center)
                            .fillMaxWidth(),
                        text = alert.areaA,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        //Prøver å resize til å passe på en linje
                        maxLines = 2,
                        overflow = TextOverflow.Visible,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = fontSize,
                            textAlign = TextAlign.Center
                        )
                        /*
                        ,inlineContent = mapOf(
                            "fontSize" to InlineTextContent(
                                Placeholder(
                                    width = fontSize,
                                    height = 0.sp,
                                    placeholderVerticalAlign = PlaceholderVerticalAlign.Top
                                )
                            )
                        )
                         */
                    )
                    /*
                    if (maxWidth > 0.dp && maxWidth < Float.MAX_VALUE.dp) {
                        fontSize = (fontSize * (maxWidth / fontSize.toPx())).coerceAtMost(fontSize)
                    }
                     */
                }
                //favoritt icon button
                var checked by remember { mutableStateOf(false) }
                IconToggleButton(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    modifier = Modifier.padding(5.dp)
                ) {
                    if (checked) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                    } else {
                        Icon(Icons.Outlined.Favorite, contentDescription = "Localized description")
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
fun Sikt_FinnTurer_card(){
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(110.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Sikt_hvit)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Galdhøpiggen", color = Sikt_sort, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(30.dp))
            Sikt_sol()
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
fun BottomSheetContent( ){
    Surface(
        modifier = Modifier.height(250.dp),
        color = Sikt_lyseblå
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Finn turer i nærheten",
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp),
                color = Sikt_sort,
                fontWeight = FontWeight.Bold
            )
            Divider(
                modifier = Modifier.padding(5.dp),
                color = Color.White)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                item {
                    Sikt_FinnTurer_card()
                }
                item {
                    Sikt_FinnTurer_card()
                }
                item {
                    Sikt_FinnTurer_card()
                }
                item {
                    Sikt_FinnTurer_card()
                }
                item {
                    Sikt_FinnTurer_card()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ModalSheetWithAnchor(
    sheetState: ModalBottomSheetState,
    showModalSheet: MutableState<Boolean>
) {
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
            })  {
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun TestComponent() {
    Scaffold(bottomBar = { Sikt_BottomBar2() }) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Sikt_orange)) {

        }
    }
}
