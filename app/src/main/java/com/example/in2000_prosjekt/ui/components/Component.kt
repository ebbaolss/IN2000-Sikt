@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import android.graphics.ColorSpace
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.PaintDrawable
import android.media.Image
import android.widget.DatePicker
import androidx.compose.animation.VectorConverter
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Settings
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.core.graphics.toColor
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
/*
import io.github.boguszpawlowski.composecalendar.*
import io.github.boguszpawlowski.composecalendar.day.Day
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.day.DefaultDay
import io.github.boguszpawlowski.composecalendar.day.NonSelectableDayState
import io.github.boguszpawlowski.composecalendar.kotlinxDateTime.toKotlinYearMonth
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.EmptySelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import io.github.boguszpawlowski.composecalendar.selection.SelectionState */
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.time.DayOfWeek
import java.time.LocalDate
import androidx.compose.material3.IconToggleButton
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.example.in2000_prosjekt.ui.database.FavoriteViewModel

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
fun Sikt_favoritt_tekst() {
    //lag topbar/overskrift for favoritter
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

fun Sikt_Favorite_card(weatherinfo: LocationInfo, nowcastinfo: NowCastInfo, sunriseinfo: SunriseInfo,
                       alertinfo: MutableList<AlertInfo>) {
    // "Refresh"-ikon er placeholder for advarsels-ikon

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
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Placeholder-ikon for advarsel:
                // Icon(Icons.Outlined.Refresh, "", tint = Sikt_mørkeblå)
                
                //Log.d("ALERT-S", "alertinfo.size: ${alertinfo.size}")
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

                Text(text = "Gaustatoppen", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                var checked by remember { mutableStateOf(false) }
                IconToggleButton(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    modifier = Modifier.padding(5.dp)
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "1884 m.o.h", fontWeight = FontWeight.Bold)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                // random km lagt inn for å teste sikt-ikonene
                Sikt_Visualisering_and_Sikt_Info(10.5, 3.3, 0.6)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sikt_FinnTurer_card(location : String, height : Int, temp : Int, skydekkeTop : Boolean, skydekkeMid : Boolean, skydekkeLow : Boolean ) {
    //på modal bottom sheet og på locationCard
    
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

                    //Text(text = "$vind m/s", fontSize = 10.sp, color = Sikt_hvit)
                }
            }
        }
    }
}

//Knapp til Instillinger for å slette alle favoritter.
@Composable
fun DeleteAllButton(viewModel: FavoriteViewModel){
    Button(
        onClick = {
            viewModel.deleteAll()
        }
    ){
        Text("Slett alle favoritter")
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
        // Tar inn antall km sikt

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
                .padding(start = 10.dp)
                .height(intrinsicSize = IntrinsicSize.Max),
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

//I vår composable funksjon som generer kalenderen vår (se neste Composable-funskjon: StaticCalender() ) så kan man bestemme innholdet til de ulike bestanddelene av en kalender, slikt som tittel, plassering av de ulike trykkbare komponetene i kalenderen, om den er scrollbar etc.
//Dette er funksjonen som bestemmer dagsinnholdet i hver dag kalenderen StaticCalender()
/*@Composable
fun dayContent(dayState: NonSelectableDayState /*,frostinfo: FrostInfo*/) : MutableList<LocalDate> {

    var alledager = mutableListOf<LocalDate>()
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(2.dp),

        border =  BorderStroke(1.dp, Sikt_hvit)
    )

    {

        Column(
            modifier = Modifier.fillMaxWidth()

        ) {

            Text(text = dayState.date.dayOfMonth.toString()+".", modifier=Modifier.padding(start = 2.4.dp) // dette er datoen
            )
            /* Logic for å velge hva slags type ikon som skal dukke opp: Ikke implementert enda: Ref. det at viewmodelen vår ikke tar inn data for øyeblikket 25.04.23: Dette er blitt diskutert med gruppa på mandag 23.04:
            if (frostinfo.typeFrost == 0) { // Picks icon to be shown depending on the sight / sikt conditions: The lower the APi value the more clear the sky is(the better the conditions)
                weathericon = R.drawable.klart
            } else if (0 < frostinfo.typeFrost && frostinfo.typeFrost < 3) {
                weathericon = R.drawable.lettskyet
            } else if (frostinfo.typeFrost > 4) {
                weathericon = R.drawable.delvis_skyet
            }
             } else if (frostinfo.typeFrost > 4) {
                weathericon = R.drawable.skyet
            }
             */

            Image(painter = painterResource(id =  R.drawable.klart),
                contentDescription = "test med fare i hver",
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(2f)
                    .wrapContentSize(Alignment.Center)
                    .size(30.dp))

            alledager.add(dayState.date) // generer en liste med innholdet i kalenderen

        }
    }


    return alledager
}
*/


//Dette er en Composable funksjon som generer en kalender med et dagsinnhold bestemt av funksjonen dayContent
//En StaticCalender er en kalender som kun presenterer en bruker for info tilknyttet hver dag i måneden. Biblioteket brukt for å generere denne kalenderen har andre typer kalendere (slikt som ukeskalendere mm.), men i henhold til kravspesifikasjonen så anså vi en StaticCalender som mest passende.
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun Sikt_Historisk_Kalender(  APIViewModel : APIViewModel = viewModel() /* frostinfo: FrostInfo*/) { // api kallet er kommentert ut fordi jeg ikke får kjørt med apikallet
    /*
    val historicCardUiState by APIViewModel.appUiState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Sikt_lyseblå),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){


        //var calenderstate : CalendarState<EmptySelectionState> = rememberCalendarState()




        StaticCalendar( firstDayOfWeek = DayOfWeek.MONDAY, modifier=Modifier.background(Sikt_lyseblå), calendarState =calenderstate, dayContent =  { it -> dayContent(
            dayState =  it/*, frostinfo = frostinfo*/)

        }
        )

        APIViewModel.getFrost( referencetimetest = ( dayContent()   )


        /*Text(text= "Picture description:")


        Text(text= "Klart:")
        //Image(painter = painterResource(id = R.drawable.klart), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/))



        Text(text= "Lettskyet:")
        //Image(painter = painterResource(id = R.drawable.lettskyet), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/)


        Text(text= "Delvis skyet:")
        //Image(painter = painterResource(id = R.drawable.delvis_skyet), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/)

        Text(text= "Skyet:")*/
        //Image(painter = painterResource(id = R.drawable.skyet), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/)



        //var kalenderdager= dayContent(dayState = DayState<EmptySelectionState>(selectionState = calenderstate.selectionState, day = Day(calenderstate.monthState.currentMonth.atDay(1))))


        //Log.d("Halladagdate", dayContent(dayState = calenderstate) .date.toString()) // Er hele datoen for en kalenderdag: 2023-05-21


        // var datoertilgjengelig: List<LocalDate> = calenderstate.monthState.currentMonth


        //var datoertilgjengelig = calenderstate.monthState.currentMonth.month


        //Log.d("calenderstate.monthState.currentMonth.month", calenderstate.monthState.currentMonth.month.toString()) // Er hele datoen for en kalenderdag: 2023-05-21

    }*/

}



@Preview(showSystemUi = true)
@Composable
fun TestComponent() {
    //Sikt_LocationCard()
}


