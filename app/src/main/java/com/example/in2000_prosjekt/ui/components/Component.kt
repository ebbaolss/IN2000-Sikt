package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import kotlinx.coroutines.launch


@Composable
fun Sikt_BottomBar(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToRules: () -> Unit, favoritt : Color, map : Color, rules : Color) {

    BottomAppBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)),
        containerColor = Sikt_hvit,

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
    //bruker denne til preview design
    BottomAppBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)),
        containerColor = Sikt_hvit,

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
fun Sikt_favoritter_card() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Sikt_lyseblå)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Galdhøpiggen", color = Sikt_sort, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(30.dp))
            Sikt_sol()
        }
    }
}


@Composable
fun Sikt_sol() {
    //bilde til fjellvettregler-screen
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
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



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
fun TestComponent() {
    Scaffold(bottomBar = { Sikt_BottomBar2()}) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Favoritter",
                color = Sikt_sort,
                fontSize = 40.sp
            )
            Divider(
                modifier = Modifier.padding(5.dp),
                color = Sikt_mellomblå)
            Sikt_favoritter_card()
            Sikt_favoritter_card()
            Sikt_favoritter_card()
            Sikt_favoritter_card()
        }

    }
}
