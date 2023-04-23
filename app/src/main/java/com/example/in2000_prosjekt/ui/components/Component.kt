package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import android.graphics.ColorSpace
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.PaintDrawable
import android.media.Image
import android.util.Log
import android.widget.DatePicker
import androidx.compose.animation.VectorConverter
import androidx.compose.foundation.*
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
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.AlertDialogDefaults.titleContentColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButtonDefaults
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
//import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
//import androidx.compose.material3.DatePickerColors Fjernet sammen med: 1.1.0-alpha04
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.colorResource
import androidx.core.graphics.toColor
import androidx.lifecycle.viewModelScope
import com.google.android.material.datepicker.MaterialDatePicker
import io.github.boguszpawlowski.composecalendar.*
import io.github.boguszpawlowski.composecalendar.day.Day
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.day.DefaultDay
import io.github.boguszpawlowski.composecalendar.day.NonSelectableDayState
import io.github.boguszpawlowski.composecalendar.kotlinxDateTime.toKotlinYearMonth
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.EmptySelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import io.github.boguszpawlowski.composecalendar.selection.SelectionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.time.DayOfWeek
import java.time.LocalDate

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

@SuppressLint("ResourceType")
@ExperimentalMaterial3Api
@Composable
fun Sikt_Historisk_Kalender_DenneGjelderikke() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(30.dp)// Ved emulator så blir kalenderen litt klippet, og overlapper
            //.height(100.dp)
            .background(Sikt_hvit), // når   .background(Sikt_hvit) så funker modifier inni datepciker
    ) {

//MAterials 3 date pciker krever versjon 1.1.0-alpha04: https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#DatePicker(androidx.compose.material3.DatePickerState,androidx.compose.ui.Modifier,androidx.compose.material3.DatePickerFormatter,kotlin.Function1,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.material3.DatePickerColors)
// ref denne stack overflowen https://stackoverflow.com/questions/75377259/how-to-change-datepicker-dialog-color-in-jetpack-compose

        //Observasjon 18.04 kl.2105: Det går visst ann å ha flere versjoner Googles Material: Jeg ahdde både: 1.0.0-alpha11 og 1.1.0-alpha04 på samtidig

        // Forsøk med Material 3: kl.21.26
       //val datePickerState  =  rememberDatePickerState(initialSelectedDateMillis = 1681852144365, initialDisplayedMonthMillis=1681852144365 , yearsRange= IntRange(2019, 2100))

       // painter= PaintDrawable()
        val image= Image(
            painter = painterResource(id = R.drawable.flood_yellow),
            contentDescription = "",
            modifier = Modifier.size(30.dp)
        )




        //mColorspace = ColorSpace(modifier = Modifier.paint(painter=  painterResource(id = R.drawable.))

/*

       val daycolor= DatePickerColors (containerColor: Color = DatePickerModalTokens.ContainerColor.toColor(),
        titleContentColor: Color = DatePickerModalTokens.HeaderSupportingTextColor.toColor(),
        headlineContentColor: Color = DatePickerModalTokens.HeaderHeadlineColor.toColor(),
        weekdayContentColor: Color = DatePickerModalTokens.WeekdaysLabelTextColor.toColor(),
        subheadContentColor: Color =
        DatePickerModalTokens.RangeSelectionMonthSubheadColor.toColor(),
        yearContentColor: Color =DatePickerModalTokens.SelectionYearUnselectedLabelTextColor.toColor(),
        currentYearContentColor: Color = DatePickerModalTokens.DateTodayLabelTextColor.toColor(),
        selectedYearContentColor: Color = DatePickerModalTokens.SelectionYearSelectedLabelTextColor.toColor(),
        selectedYearContainerColor: Color =DatePickerModalTokens.SelectionYearSelectedContainerColor.toColor(),
        dayContentColor: Color = DatePickerModalTokens.DateUnselectedLabelTextColor.toColor(),
        // TODO: Missing token values for the disabled colors.
        disabledDayContentColor: Color = dayContentColor.copy(alpha = 0.38f),
        selectedDayContentColor: Color = DatePickerModalTokens.DateSelectedLabelTextColor.toColor(),
        // TODO: Missing token values for the disabled colors.
        disabledSelectedDayContentColor: Color = selectedDayContentColor.copy(alpha = 0.38f),
        selectedDayContainerColor: Color =
        DatePickerModalTokens.DateSelectedContainerColor.toColor(),
        // TODO: Missing token values for the disabled colors.
        disabledSelectedDayContainerColor: Color = selectedDayContainerColor.copy(alpha = 0.38f),
        todayContentColor: Color = DatePickerModalTokens.DateTodayLabelTextColor.toColor(),
        todayDateBorderColor: Color =
        DatePickerModalTokens.DateTodayContainerOutlineColor.toColor())
*/


        val p = painterResource(id = R.drawable.flood_yellow)

        val gh= colorResource(id = R.drawable.flood_yellow)
/*
        val alternative= DatePickerDefaults.colors(dayContentColor=gh)


        //DatePicker(datePickerState=datePickerState, colors=alternative)


        //val color= Color(ColorSpace=mColorspace )// colospace


        val b = ColorSpace.RenderIntent.ABSOLUTE
        val t= DatePickerDefaults.colors(dayContentColor=Color.Green)
        val h= DatePickerDefaults.colors(dayContentColor=gh)
        /*


        val l= DatePickerDefaults.colors(dayContentColor=Color.VectorConverter{})// vektor
        val l= DatePickerDefaults.colors(dayContentColor=Color.VectorConverter{})// vektor

*/


        val datePickerFormater=  DatePickerFormatter(
             shortFormat= "test 1", mediumFormat=" Test 2",
        monthYearFormat= "Saturday, March 27, 2021"

        )
 */

        // DatePicker(datePickerState=datePickerState, modifier=Modifier.padding(30.dp)// Ved emulator så blir kalenderen mikroskopisk liten
        //            .height(100.dp),  ) - Funket dårlig for nå så prøv heller å bruker default colors for å sette bilde som default color






     //Dette var:  https://github.com/boguszpawlowski/ComposeCalendar?fbclid=IwAR3i_MvLSzs-8v23gcIa8MSNFfLaBm9bWYAiIGhmTve-P59dj628WdFK7Lc
   //  @Composable
     // fun MainScreen() {
        //StaticCalendar(modifier=Modifier.background(Color.Green))

        //}
        /* forsøk med Material  https://m2.material.io/components/date-pickers Denne bruker et annet google bibliotek:https://developer.android.com/reference/com/google/android/material/datepicker/MaterialDatePicker ikke det samme som modalBottomSheetLayout
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        datePicker.show() // funket ikke, kl.2146


https://medium.com/@segunfrancis/how-to-create-material-date-and-time-pickers-in-android-18ecd246838b */

       // MaterialDatePicker.Builder.datePicker().setTitleText("Select date of birth").build().show()




        Text(text = "Kalenderen")
    }
}


@Composable
fun dayContent(dayState: NonSelectableDayState /*,frostinfo: FrostInfo*/) {

    Card(
        modifier = Modifier.aspectRatio(1f)// Dette er en test for å se på om
            .padding(2.dp), // jerg skjønner dette er padding mellom hver card, men fremdeels

        border =  BorderStroke(1.dp, Sikt_hvit)
    )

    {

        Column(
            modifier = Modifier//clickable {},
            //contentAlignment = Alignment.Center, // åja tror det er denne, f
        ) {

            Text(text = dayState.date.dayOfMonth.toString()+".", modifier=Modifier.padding(start = 1.5.dp)//.wrapContentHeight( //  modifier = Modifier.align(Alignment.TopCenter)
                //  Alignment.TopStart).aspectRatio(.1f).size(0.1.dp)
            )

//Denne lå har før, og fjern for loop
//                    Image(painter = painterResource(id = R.drawable.flood_orange), contentDescription = "test med fare i hver", contentScale = ContentScale.FillWidth, modifier = Modifier.fillMaxWidth())

            var i = 0



            // weather icon to be shown in each calender date
            var weathericon = R.drawable.partlycloudy


            /*
            if (frostinfo.typeFrost == 0) { // Picks icon to be shown depending on the sight / sikt conditions: The lower the APi value the more clear the sky is(the better the conditions)
                weathericon = R.drawable.sunny
            } else if (0 < frostinfo.typeFrost && frostinfo.typeFrost < 4 ) {
                weathericon = R.drawable.partlycloudy
            } else if (frostinfo.typeFrost > 4) {
                weathericon = R.drawable.cloudy
            }
             */

            for ( i in 1..dayState.date.lengthOfMonth()){
                // innafor denne så har jeg lyst til
                //Gjøre et APi kall, også vis frem et bilde// if setning fra det over

                Image(painter = painterResource(id = weathericon), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.padding(start = 8.dp).wrapContentSize(Alignment.Center)/*.fillMaxWidth(0.7f)*/)
            }

        }




    }

    Log.d("Halladagdate", dayState.date.toString())
    Log.d("Halladagmonthvalue", dayState.date.monthValue.toString())
    Log.d("HalladagisCurrent", dayState.isCurrentDay.toString())
    Log.d("HalladagIsfromcurrent monht", dayState.isFromCurrentMonth.toString())



}






//Dette var:  https://github.com/boguszpawlowski/ComposeCalendar?fbclid=IwAR3i_MvLSzs-8v23gcIa8MSNFfLaBm9bWYAiIGhmTve-P59dj628WdFK7Lc
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun Sikt_Historisk_Kalender(/* frostinfo: FrostInfo*/) { // api kallet er kommentert ut fordi jeg ikke får kjørt med apikallet
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Sikt_lyseblå),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){


        var testie : CalendarState<EmptySelectionState> = rememberCalendarState()


        StaticCalendar( firstDayOfWeek = DayOfWeek.MONDAY, modifier=Modifier.background(Sikt_lyseblå), calendarState =testie, dayContent =  { it -> dayContent(
            dayState =  it/*, frostinfo = frostinfo*/) }
        )

        Text(text= "Picture description:")


        Text(text= "Clear conditions/clear sky:")
        Image(painter = painterResource(id = R.drawable.sunny), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/)



        Text(text= "Partly cloudy:")
        Image(painter = painterResource(id = R.drawable.partlycloudy), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/)


        Text(text= "Cloudy:")
        Image(painter = painterResource(id = R.drawable.cloudy), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/)

        var dag : Day   //(date= LocalDate , isCurrentDay = true , isFromCurrentMonth=  true ) // trenger ikke være noe

        /*
            var ww =  DefaultDay(
                state= DayState(day= dag,selectionState = testie.selectionState), // hva skal day= Day være : Kanskje dagens dato: Som vi vet er:
                modifier = Modifier ,
                selectionColor = androidx.compose.material.MaterialTheme.colors.secondary, // hightlight fargen på en valgt dag
                currentDayColor= androidx.compose.material.MaterialTheme.colors.primary, // farget på dagens dato
                onClick  = {},// ønsker ikke at noe skal skje

            )

             */


    }

}


/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun sikt_tittelDatepciker(state: DatePickerState) : Unit {

    state.
    DateRangePickerDefaults.DateRangePickerTitle(
        state = state,
        modifier = Modifier.padding(10.dp)
    )

}

 */

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

        //Sikt_Historisk_Kalender()

        Sikt_LoctationCard_Topper_i_naerheten()

    }
}

/*

@Preview(showSystemUi = true)
@Composable
fun TestComponent() {
    Sikt_LocationCard()
}

 */


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun TestDatePicker() {
    //Sikt_Historisk_Kalender()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Sikt_hvit),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){

        SelectableCalendar(modifier=Modifier.background(Color.Green))

    }
}


@Preview(showSystemUi = true)
@Composable
fun SelectableCalendarSample() {
    val calendarState = rememberSelectableCalendarState() // Lager en dynamic selection state

    manedTilSelectableKalendern(calendarStatey = calendarState)// okei,

    var g = calendarState.monthState.currentMonth // ToString gjør ikke monthstate til en måned, den gjør den til Klassens minne adress
    var i = calendarState.monthState.currentMonth.month // ToString gjør ikke monthstate til en måned, den gjør den til Klassens minne adress
    var r = calendarState.monthState.currentMonth.year // ToString gjør ikke monthstate til en måned, den gjør den til Klassens minne adress
    var n = calendarState.monthState.currentMonth.monthValue // ToString gjør ikke monthstate til en måned, den gjør den til Klassens minne adress
    var w = calendarState.monthState.currentMonth.lengthOfMonth() // ToString gjør ikke monthstate til en måned, den gjør den til Klassens minne adress
    var dagensdato = calendarState.monthState.currentMonth.month// dagens dato

        /*
    Column(
        Modifier.verticalScroll(rememberScrollState())
    ) {
        SelectableCalendar(calendarState = calendarState, firstDayOfWeek = DayOfWeek.MONDAY, modifier = Modifier.background(Sikt_lyseblå))

        SelectionControls(selectionState = calendarState.selectionState)
// kl.17.11, alle de 5 boksene under var skrevet kl.16.28
        Text(text=" calendarState.monthState.currentMonth: " + g +" Nebil1")
        Text(text=" calendarState.monthState.currentMonth.month: " + i +" Nebil2")
        Text(text="  calendarState.monthState.currentMonth.year : " + r +" Nebil3")
        Text(text=" calendarState.monthState.currentMonth.monthValue: " + n +" Nebil3")
        Text(text=" calendarState.monthState.currentMonth.lengthOfMonth(): " + w +"Nebs4")

    }

         */
}


@Composable
private fun SelectionControls(
    selectionState: DynamicSelectionState,
) {
    Text(
        text = "Calendar Selection Mode",
        style = MaterialTheme.typography.bodyMedium,
    )
    SelectionMode.values().forEach { selectionMode ->
        Row(modifier = Modifier.fillMaxWidth()) {

            RadioButton(
                selected = selectionState.selectionMode == selectionMode,
                onClick = { selectionState.selectionMode = selectionMode },
                colors = RadioButtonDefaults.colors()

            )
            Text(text = selectionMode.name) // generer 4 RadioButtons under hverandre
            Spacer(modifier = Modifier.height(4.dp))
        }
    }

    Text(
        text = "Selection: ${selectionState.selection.joinToString { it.toString() }}", // Printer teksten "Selection:"
        style = MaterialTheme.typography.bodyMedium //MaterialTheme.typography.h6,
    )


/*
    Text(// dette skal være hele måneden forran oss
        text = "Info nebil test 1: ${selectionState.selection.listIterator()}", // Dette Skal være hele måneden

        style = MaterialTheme.typography.bodyMedium //MaterialTheme.typography.h6,
    )


   Text(// dette skal være hele måneden forran oss
        text = "Info nebil test 2: ${selectionState.selection.toString()}", // Dette Skal være hele måneden

        style = MaterialTheme.typography.bodyMedium //MaterialTheme.typography.h6,
    )
    Text(// dette skal være hele måneden forran oss
        text = "Info nebil test 3: ${selectionState.selectionMode.name}", // Dette Skal være hele måneden
        style = MaterialTheme.typography.bodyMedium //MaterialTheme.typography.h6,
    )
    Text(// dette skal være hele måneden forran oss
        text = "Info nebil test 4: ${selectionState.isDateSelected(LocalDate.now()) }" + " ${selectionState.selection.asReversed()}", // To skikkelig spennende metoder
        style = MaterialTheme.typography.bodyMedium //MaterialTheme.typography.h6,
    )


    // det du ønsker er en List<LocaleDate> av måneden du er i:
    // hvor får du måneden du er i
    val datoer1 : MutableList<LocalDate> = mutableListOf() // tom liste av typen: dag
    val datoer2 : MutableList<LocalDate> = mutableListOf() // tom liste av typen: dag
    val datoer3 : MutableList<LocalDate> = mutableListOf() // tom liste av typen: dag

    var i : Int =0
  *//*  while(selectionState.selection.listIterator().hasNext()){

        datoer1.add(index= i, selectionState.selection.listIterator().previous() )
      //  datoer2.add(index= i, selectionState.selection.lastIndexOf() )
    }*//*

    selectionState.selection.listIterator().forEach{ dagDate -> // for each or å fylle en fiktiv måned: Har man seriøst ikke andre måter å finne måeneded man er i, oppgave for lørdag den 22.02 Finn hvor månedinfoen er for current dato

        datoer3.add(dagDate)
    }

    Text(// dette skal være hele måneden forran oss
        text = "Info nebil test 5: ${datoer1.toString() }", // Dette Skal være hele måneden
        style = MaterialTheme.typography.bodyMedium //MaterialTheme.typography.h6,
    )
      Text(// dette skal være hele måneden forran oss
        text = "Info nebil test 6: ${datoer3.toString()}", // Dette Skal være hele måneden
        style = MaterialTheme.typography.bodyMedium //MaterialTheme.typography.h6,
    )*/

//målet med disse testene er å få måneden vi er i uten å røre noe som helt, uten å selecte noenting

// Oppgaver for lørdag 22.04
    //Oppgave 1:
// sjekke ut documentasjonssiden til Selection state og Localdate
//Oppgave 2:
//prøv datoer3 med betingelsen:  !   selectionState.selection.isDateSelected(localDate også skriv alle 12 månedene)

// Oppgave 3: Sjekk ut begge over med forEach setning og lær om lambda utrykk av phillip lacknin

}

fun manedTilSelectableKalendern (calendarStatey: CalendarState<DynamicSelectionState>) : Unit {

    Log.d("Halla1", calendarStatey.monthState.currentMonth.toString())
    Log.d("Halla1", calendarStatey.monthState.currentMonth.lengthOfMonth().toString())
    Log.d("Halla2", calendarStatey.monthState.currentMonth.toKotlinYearMonth().toString())
    Log.d("HallamånedTEKST", calendarStatey.monthState.currentMonth.month.toString())
    Log.d("HallamånedTall", calendarStatey.monthState.currentMonth.month.value.toString())
    Log.d("Hallayear", calendarStatey.monthState.currentMonth.year.toString())


    // Wow, lørdag 22.04 funksjoen : .toKotlinYearMonth().

    //kan man omgjøre en selectable calender til en unselectable en
    // Kan vi få currenth month for et static calender
}

fun manedTilSTATISKKalendern (StatiskKalender: CalendarState<EmptySelectionState>) : Unit {
/*
    Log.d("StatiskKalenderHalla3", StatiskKalender.monthState.currentMonth.toString())
    Log.d("Halla4", StatiskKalender.monthState.toString())
    Log.d("Halla5", StatiskKalender.monthState.currentMonth.toKotlinYearMonth().toString())*/

    // Wow, lørdag 22.04 funksjoen : .toKotlinYearMonth().

    //kan man omgjøre en selectable calender til en unselectable en
    // Kan vi få currenth month for et static calender
}

@Composable
fun minDayComposable (calendarStatey: CalendarState<DynamicSelectionState>) { //
    // Funksjoner
// DEnne skal være et card: Med bare et tall dagens dato, og et bilde rett under Das it


    // url builder:2021-05-01%2F2021-05-31
    val beginningofmonth= "01"
    val endofmonth= calendarStatey.monthState.currentMonth.lengthOfMonth().toString() // 28, 30 eller 31
    val month= "0"+calendarStatey.monthState.currentMonth.month.value.toString() // nåværende måned: 05 = mai
    val year= calendarStatey.monthState.currentMonth.year.toString() // 2022,2023

    val referencetiime= year+"-"+month+"-"+beginningofmonth + "%2F"+year+"-"+month+"-"+endofmonth

    val source = "SN18700"
    var elements = "air_temperature"

/*
    return viewModelScope.async(Dispatchers.IO) {

        val frost = dataFrost.fetchFrostTemp(elements, referencetime, source)
        val frostPolygon = dataFrost.fetchApiSvarkoordinater(2.toString(), 2.toString())

        val typeFrost = frost.type
        val long = frostPolygon.data?.get(0)?.geometry?.coordinates?.get(0)
        val lat = frostPolygon.data?.get(0)?.geometry?.coordinates?.get(1)

        Log.d("typefrost", typeFrost.toString())
        Log.d("lat", lat.toString())
        Log.d("long", long.toString())

        val frostF = FrostInfo(
            typeFrost = typeFrost.toString(), //ikke egt ha toString her
            longFrost = long!!,
            latFrost = lat!!,
        )
        return@async frostF

 */

// Oppgave 3.1 Lag en Day Composable: Se documentasjno: Daycontent inni StaticCalender

        // et tall // kanske bruk den LocalDate staandard defuelt greia
        // et bilde av et været
    //DayState
    calendarStatey.monthState
    calendarStatey.monthState.currentMonth
    /*
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(2.dp),
        elevation = if (calendarStatey.isFromCurrentMonth) 4.dp else 0.dp,
        border = if (state.isCurrentDay) BorderStroke(1.dp, currentDayColor) else null,
        contentColor = if (isSelected) selectionColor else contentColorFor(
            backgroundColor = androidx.compose.material.MaterialTheme.colors.surface
        ){

            Box(
                modifier = Modifier.clickable {
                    onClick(date)
                    selectionState.onDateSelected(date)
                },
                contentAlignment = Alignment.Center,
            ) {

               Text(text = date.dayOfMonth.toString())

                Image(painter = painterResource(id = R.drawable.flood_orange), contentDescription = "test med fare i hver", contentScale = ContentScale.FillWidth, modifier = Modifier.fillMaxWidth())
            }


        }

     */


}


