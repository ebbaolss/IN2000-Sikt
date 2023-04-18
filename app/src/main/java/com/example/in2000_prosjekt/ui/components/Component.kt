package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Menu
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

@Composable
fun Sikt_BottomBar(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToSettings: () -> Unit, onNavigateToRules: () -> Unit, favoritt : Color, map : Color, settings : Color, rules : Color) {

    BottomAppBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)),
        containerColor = Sikt_hvit,

        ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Column (horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(70.dp)
            ){
                IconButton(onClick = { onNavigateToMap() }) {
                    var backroundColor = Sikt_hvit
                    if (map == Sikt_mellomblå) {
                        backroundColor = Sikt_lyseblå
                    }
                    Icon(
                        Icons.Outlined.LocationOn,
                        contentDescription = "Localized description",
                        tint = Sikt_mørkeblå,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(backroundColor)
                            .padding(5.dp))
                }
                Text(text = "Utforsk", fontSize = 13.sp,)
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
      modifier = Modifier.fillMaxWidth()
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
            ) {
                Image(
                    modifier = Modifier
                        .padding(),
                    painter = painterResource(id = id),
                    contentDescription = "alert",
                    alignment = Alignment.TopStart
                )
                Text(text = alert.areaA, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Text(text = "Type: " + alert.typeA, fontFamily = FontFamily.Monospace)
            Text(text = "Beskrivelse: "+ alert.descriptionA, fontFamily = FontFamily.Monospace)
            Text(text = "Konsekvens: " + alert.consequenseA, fontFamily = FontFamily.Monospace)
            Text(text = "Anbefaling: " + alert.recomendationA, fontFamily = FontFamily.Monospace)


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
