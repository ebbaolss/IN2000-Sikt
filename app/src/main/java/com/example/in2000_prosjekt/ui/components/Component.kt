package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import android.media.Image
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.*
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
                        modifier = Modifier
                            .size(120.dp)
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
fun Sikt_Favorite_card(){
    Card(
        modifier = Modifier.size(height = 336.dp, width = 320.dp),
        colors = CardDefaults.cardColors(Sikt_lyseblå)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box() {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Outlined.Refresh, "", tint = Sikt_mørkeblå) //fyll symbol, bytter senere
                        Text(text = "Gaustatoppen", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                        Icon(Icons.Filled.Favorite, "", tint = Sikt_mørkeblå)
                    }
                    Text(text = "1884 m.o.h", fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box() {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Sikt_Datavisualisering_Card(1884, -10, 7,true, false, true)
                        Column(
                            modifier = Modifier.size(height = 220.dp, width = 150.dp),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "2000-5000 m.o.h")
                            Text(text = "Sikt: ")
                            Text(text = "1000-2000 m.o.h")
                            Text(text = "Sikt: ")
                            Text(text = "0-1000 m.o.h")
                            Text(text = "Sikt: ")
                        }
                    }
                }
            }
        }
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
    alertinfo: MutableList<AlertInfo>, frostinfo: FrostInfo
) {
    val varsel = "0" //midlertidlig, egt metAlert som skal brukes

    Card(
        modifier = Modifier.size(height = 336.dp, width = 320.dp),
        colors = CardDefaults.cardColors(Sikt_lyseblå)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

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

            Box() {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Outlined.Refresh, "", tint = Sikt_mørkeblå) //fyll symbol, bytter senere
                        Text(text = "Galdhøpiggen", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                        Icon(Icons.Filled.Favorite, "", tint = Sikt_mørkeblå)
                    }
                    Text(text = "2469 m.o.h", fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

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
            Text(text = "Type frost: ${frostinfo.typeFrost}", fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier
                .height(30.dp))
            Text(text = "Coordinates frost: ${frostinfo.latFrost}, ${frostinfo.longFrost}", fontFamily = FontFamily.Monospace)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sikt_FinnTurer_card(height : Int, temp : Int, vind : Int, skydekkeTop : Boolean, skydekkeMid : Boolean, skydekkeLow : Boolean ) {

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
    ) {
        Box(modifier = Modifier.size(height = 73.dp, width = 57.dp)) {

            Image(
                painter = painterResource(id = R.drawable.illustrasjon_background),
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
            Text(text = "$temp°", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Sikt_hvit,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(5.dp))

            Box(modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(5.dp)) {
                Column() {
                    Image(
                        painter = painterResource(id = R.drawable.vind_icon),
                        contentDescription = "",
                        modifier = Modifier.size(10.dp)
                    )
                    Text(text = "$vind m/s", fontSize = 10.sp, color = Sikt_hvit)
                }
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
                    Sikt_FinnTurer_card(2469, 3, 7,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card(2469, 3, 7,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card(2469, 3, 7,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card(2469, 3, 7,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card(2469, 3, 7,true, false, true)
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

@Composable
fun Sikt_Datavisualisering_Card(height : Int, temp : Int, vind : Int, skydekkeTop : Boolean, skydekkeMid : Boolean, skydekkeLow : Boolean ) {

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

    Card() {
        Box(
            modifier = Modifier.size(width = 170.dp, height = 220.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.illustrasjon_background),
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
                .align(Alignment.BottomEnd)
                .padding(10.dp)
                .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
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
fun Sikt_LocationCard_Hour() {
    Card(
        modifier = Modifier
            .width(70.dp)
            .height(80.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Sikt_hvit)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "12:00", color = Sikt_sort, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(30.dp))
            Sikt_sol()
        }
    }
}

@Composable
fun Sikt_LocationCard_NextDays() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(start = 30.dp, end = 30.dp, bottom = 20.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Sikt_hvit)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Idag", color = Sikt_sort, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "Lettskyet", color = Sikt_sort, fontSize = 12.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Fredag", color = Sikt_sort, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "Lettskyet", color = Sikt_sort, fontSize = 12.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Lørdag", color = Sikt_sort, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "Lettskyet", color = Sikt_sort, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun Sikt_LoctationCard_Topper_i_naerheten() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(start = 20.dp, end = 20.dp, top = 5.dp),
    ) {
        Text(text = "Topper i nærheten: ", modifier = Modifier.align(Alignment.TopStart), fontWeight = FontWeight.Bold)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            item {
                Sikt_FinnTurer_card(450, -10, 0,true, false, true)
            }
            item {
                Sikt_FinnTurer_card(550, 3, 3,false, true, false)
            }
            item {
                Sikt_FinnTurer_card(1100, 23, 9,false, false, true)
            }
            item {
                Sikt_FinnTurer_card(1670, -1, 15,false, false, false)
            }
            item {
                Sikt_FinnTurer_card(2469, 6, 2,true, true, true)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sikt_LocationCard(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Sikt_lyseblå),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box() {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.Refresh, "", tint = Sikt_mørkeblå) //fyll symbol, bytter senere
                    Text(text = "Galdhøpiggen", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                    Icon(Icons.Filled.Favorite, "", tint = Sikt_mørkeblå)
                }
                Text(text = "2469 m.o.h", fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box() {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Sikt_Datavisualisering_Card(2469, 3, 7,true, false, true)
                    Column(
                        modifier = Modifier.size(height = 220.dp, width = 150.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "2000-5000 m.o.h")
                        Text(text = "Sikt: ")
                        Text(text = "1000-2000 m.o.h")
                        Text(text = "Sikt: ")
                        Text(text = "0-1000 m.o.h")
                        Text(text = "Sikt: ")
                    }
                }
            }
        }
        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            item {
                Sikt_LocationCard_Hour()
            }
            item {
                Sikt_LocationCard_Hour()
            }
            item {
                Sikt_LocationCard_Hour()
            }
            item {
                Sikt_LocationCard_Hour()
            }
            item {
                Sikt_LocationCard_Hour()
            }
            item {
                Sikt_LocationCard_Hour()
            }
        }
        Sikt_LocationCard_NextDays()
        Sikt_LoctationCard_Topper_i_naerheten()

    }
}

@Composable
fun Sikt_Historisk_Kalender() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
            .height(100.dp)
            .background(Sikt_hvit),
    ) {
        Text(text = "Kalender")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sikt_HistoriskCard(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Sikt_lyseblå),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box() {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.Refresh, "", tint = Sikt_mørkeblå) //fyll symbol, bytter senere
                    Text(text = "Galdhøpiggen", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                    Icon(Icons.Filled.Favorite, "", tint = Sikt_mørkeblå)
                }
                Text(text = "2469 m.o.h", fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box() {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Sikt_Datavisualisering_Card(2469, -11, 4, true, false, true)
                    Column(
                        modifier = Modifier.size(height = 220.dp, width = 150.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "2000-5000 m.o.h")
                        Text(text = "Sikt: ")
                        Text(text = "1000-2000 m.o.h")
                        Text(text = "Sikt: ")
                        Text(text = "0-1000 m.o.h")
                        Text(text = "Sikt: ")
                    }
                }
            }
        }

        Sikt_Historisk_Kalender()

        Sikt_LoctationCard_Topper_i_naerheten()

    }
}


@Preview(showSystemUi = true)
@Composable
fun TestComponent() {
    Sikt_LocationCard()
}