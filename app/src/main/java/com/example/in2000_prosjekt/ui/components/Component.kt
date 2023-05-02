@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import android.util.Log
import android.graphics.ColorSpace
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.PaintDrawable
import android.media.Image
import android.widget.DatePicker
import androidx.compose.animation.VectorConverter

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
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
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.example.in2000_prosjekt.ui.data.DataSource
import com.example.in2000_prosjekt.ui.data.DataSourceSunrise
import com.example.in2000_prosjekt.ui.data.ImplementedWeatherRepository
import com.example.in2000_prosjekt.ui.database.FavoriteViewModel
import com.example.in2000_prosjekt.ui.uistate.MapUiState

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
                    var iconChosen = R.drawable.outline_place_outline
                    if (map) {
                        iconChosen = R.drawable.baseline_place_filled
                    }
                    Icon(
                        painter = painterResource(iconChosen),
                        contentDescription = "Localized description",
                        tint = iconfarge,
                        modifier = Modifier
                            .clip(CircleShape)
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
                    var iconChosen = Icons.Outlined.Favorite
                    if (favoritt) {
                        iconChosen = Icons.Filled.Favorite
                    }
                    Icon(
                        iconChosen,
                        contentDescription = "Localized description",
                        tint = iconfarge,
                        modifier = Modifier
                            .clip(CircleShape)
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
                    var iconChosen = R.drawable.outline_view_list_outlined
                    if (rules) {
                        iconChosen = R.drawable.baseline_view_list_filled
                    }
                    Icon(
                        painter = painterResource(iconChosen),
                        "",
                        tint = iconfarge,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
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
                    var iconChosen = R.drawable.outline_info_outlined
                    if (settings) {
                        iconChosen = R.drawable.baseline_info_filled
                    }
                    Icon(
                        painterResource(iconChosen),
                        "",
                        tint = iconfarge,
                        modifier = Modifier
                            .clip(CircleShape)
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
                        painter = painterResource(id = R.drawable.outline_info_outlined),
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
fun Sikt_Header(location : String , alertinfo: MutableList<AlertInfo> ) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var openDialog by remember {
            mutableStateOf(false)
        }

        if (alertinfo.size != 0){
            AlertButton( alertinfo.get(0).alertTypeA, alertinfo.get(0).alertLevelA){
                openDialog = true
            }
        }

        if (openDialog){
            AlertDialog(alertinfo = alertinfo){
                openDialog = false
            }
        }
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
fun Sikt_skyillustasjon() {
    Image(
        painter = painterResource(id = R.drawable.clounds_image),
        contentDescription = "sol",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
        )
}

@OptIn(ExperimentalMaterial3Api::class)
fun LazyListScope.Sikt_Favorite_card(weatherinfo: MutableList<LocationInfo>, nowcastinfo: MutableList<NowCastInfo>, alertInfo: MutableList<MutableList<AlertInfo>>) {
    //favorites er en mutableList med LocationInfo kan derfor kalle
    // favorite.temperatureL etc.
    items(weatherinfo.size) {
        weatherinfo.forEach { favorite ->
            val location = weatherinfo[it]
            val nowcast = nowcastinfo[it]
            val alertInfo = alertInfo[it]

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

                    Sikt_Header("fjelltopp", alertInfo)
                    Sikt_MountainHight("1884")
                    illustrasjon(
                        height = 1280,
                        temp = nowcast.temperatureNow,
                        vind = nowcast.windN,
                        weatherHigh = location.cloud_area_fraction_high,
                        weatherMid = location.cloud_area_fraction_medium,
                        weatherLow = location.cloud_area_fraction_low
                    )
                }
            }
        }
    }
}

fun LazyListScope.Sikt_Turer_I_Naerheten(mountains: MutableList<MapUiState.Mountain>, locationInfo: LocationInfo, nowCastInfo: NowCastInfo){
    //location : String, height : Int, temp : Int

    items(mountains.size) {
        mountains.forEach { tur ->
            val temp = nowCastInfo.temperatureNow

            Card(
                colors = CardDefaults.cardColors(Sikt_bakgrunnblå),
                modifier = Modifier.padding(20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.size(100.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.turer_i_naerheten),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize()
                        )
                        Text(
                            text = "${temp}°",
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            color = Sikt_sort,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "${tur.name}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Sikt_sort,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
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
fun illustrasjon(height : Int?, temp : Float, vind : Float, weatherHigh : Float, weatherMid : Float, weatherLow : Float){

    fun getHeightVisuals(height: Int?) : Int {
        return if (height != null) {
            if (height < 500) {
                R.drawable.topp_under500
            } else if (height < 1000) {
                R.drawable.topp1000to500
            } else if (height < 1500) {
                R.drawable.topp1500to1000
            } else if (height < 2000) {
                R.drawable.topp2000to1500
            } else {
                R.drawable.topp_over2000
            }
        } else {
            R.drawable.topp_under500
        }
    }

    // Klart vær = God sikt = Sikt på mer enn 10 km (INGEN SKYER) under 25%
    // Lettskyet = Moderat sikt = Sikt på 4-10 km (LITEN SKY) 25% - 50%
    // Delvis skyet = Dårlig sikt = Sikt på 1-4 km (STOR SKY) 50% - 75%
    // Skyet = Tåke = Sikt på mindre enn 1 km (STOR + LITEN SKY) over 100%

    fun getHighClouds(highclouds: Float): Int {
        return if (highclouds >= 75) {
            R.drawable.clouds_high_both
        } else if (highclouds >= 50) {
            R.drawable.clouds_high_big
        } else if (highclouds >= 25 ) {
            R.drawable.clouds_high_small
        } else {
            R.drawable.klart
        }
    }

    fun getMidClouds(midclouds: Float): Int {
        return if (midclouds >= 75) {
            R.drawable.clouds_mid_both
        } else if (midclouds >= 50) {
            R.drawable.clouds_mid_big
        } else if (midclouds>= 25) {
            R.drawable.clouds_mid_small
        } else {
            R.drawable.klart
        }
    }

    fun getLowClouds(lowclouds: Float): Int {
        return if (lowclouds >= 75) {
            R.drawable.clouds_low_both
        } else if (lowclouds >= 50) {
            R.drawable.clouds_low_big
        } else if (lowclouds >= 25) {
            R.drawable.clouds_low_small
        } else {
            R.drawable.klart
        }
    }

    fun getRightWeather(weather: Float): String {
        return if (weather >= 75) {
            "Meget dårlig sikt"
        } else if (weather >= 50) {
            "Dårlig sikt"
        } else if (weather >= 25) {
            "Lettskyet"
        } else {
            "Klart vær"
        }
    }

    fun getRightKm(km: Float): String {
        return if (km >= 75) {
            "> 1 km sikt"
        } else if (km >= 50) {
            "1-4 km sikt"
        } else if (km >= 25) {
            "4-10 km sikt"
        } else {
            "< 10 km sikt"
        }
    }

    Box(
        modifier = Modifier.aspectRatio(1f)
    ){
        Image(
            painter = painterResource(id = R.drawable.new_background),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = getHeightVisuals(height)),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = getHighClouds(weatherHigh)),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = getMidClouds(weatherMid)),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = getLowClouds(weatherLow)),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.TopStart)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.vind_icon),
                contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
            Text(text = "$vind m/s", fontSize = 12.sp, color = Sikt_sort)
        }
        Text(
            text = "$temp°",
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            color = Sikt_sort,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterEnd)
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(text = "2000 - 5000 m.o.h.", fontSize = 12.sp, color = Sikt_sort)
                Text(text = getRightKm(weatherHigh), fontSize = 16.sp, color = Sikt_sort, fontWeight = FontWeight.Bold)
                Text(text = getRightWeather(weatherHigh), fontSize = 12.sp, color = Sikt_sort)
            }
            Divider(thickness = 1.dp, color = Sikt_sort, modifier = Modifier.width(100.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(text = "1000 - 2000 m.o.h.", fontSize = 12.sp, color = Sikt_sort)
                Text(text = getRightKm(weatherMid), fontSize = 16.sp, color = Sikt_sort, fontWeight = FontWeight.Bold)
                Text(text = getRightWeather(weatherMid), fontSize = 12.sp, color = Sikt_sort)
            }
            Divider(thickness = 1.dp, color = Sikt_sort, modifier = Modifier.width(100.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(text = "0 - 1000 m.o.h.", fontSize = 12.sp, color = Sikt_sort)
                Text(text = getRightKm(weatherLow), fontSize = 16.sp, color = Sikt_sort, fontWeight = FontWeight.Bold)
                Text(text = getRightWeather(weatherLow), fontSize = 12.sp, color = Sikt_sort)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TestComponent() {

    //Sikt_FinnTurer_card("test",550, 3,false, true, false)

}
