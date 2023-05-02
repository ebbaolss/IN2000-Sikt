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
fun Sikt_Header(location : String /*,alertType : String, alertLevel : String*/) {
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
@Composable
fun Sikt_Favorite_card(weatherinfo: LocationInfo, nowcastinfo: NowCastInfo, sunriseinfo: SunriseInfo, alertinfo: MutableList<AlertInfo> ) {
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

            Sikt_Header("fjelltopp" /*, alertType : String, alertLevel : String */)
            Sikt_MountainHight("1884")
        }
    }
}

@Composable
fun Sikt_Turer_I_Naerheten(location : String, height : Int, temp : Int){
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
                /*Image(
                    painter = painterResource(id = R.drawable.turer_i_naerheten),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )*/
                Text(
                    text = "7°",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = Sikt_sort,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "fjelltopp",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Sikt_sort,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
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
fun illustrasjon(height : Int?, temp : Float, vind : Float, weatherHigh : String, weatherMid : String, weatherLow : String){

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

    // Klart vær = God sikt = Sikt på mer enn 10 km (INGEN SKYER)
    // Lettskyet = Moderat sikt = Sikt på 4-10 km (LITEN SKY)
    // Delvis skyet = Dårlig sikt = Sikt på 1-4 km (STOR SKY)
    // Skyet = Tåke = Sikt på mindre enn 1 km (STOR + LITEN SKY)

    fun getHighClouds(highclouds: String): Int {
        return if (highclouds == "skyet") {
            R.drawable.clouds_high_both
        } else if (highclouds == "delvisskyet") {
            R.drawable.clouds_high_big
        } else if (highclouds == "lettskyet") {
            R.drawable.clouds_high_small
        } else {
            R.drawable.klart
        }
    }

    fun getMidClouds(midclouds: String): Int {
        return if (midclouds == "skyet") {
            R.drawable.clouds_mid_both
        } else if (midclouds == "delvisskyet") {
            R.drawable.clouds_mid_big
        } else if (midclouds == "lettskyet") {
            R.drawable.clouds_mid_small
        } else {
            R.drawable.klart
        }
    }

    fun getLowClouds(lowclouds: String): Int {
        return if (lowclouds == "skyet") {
            R.drawable.clouds_low_both
        } else if (lowclouds == "delvisskyet") {
            R.drawable.clouds_low_big
        } else if (lowclouds == "lettskyet") {
            R.drawable.clouds_low_small
        } else {
            R.drawable.klart
        }
    }

    fun getRightWeather(weather: String): String {
        return if (weather == "skyet") {
            "Meget dårlig sikt"
        } else if (weather == "delvisskyet") {
            "Dårlig sikt"
        } else if (weather == "lettskyet") {
            "Lettskyet"
        } else {
            "Klart vær"
        }
    }

    fun getRightKm(km: String): String {
        return if (km == "skyet") {
            "> 1 km sikt"
        } else if (km == "delvisskyet") {
            "1-4 km sikt"
        } else if (km == "lettskyet") {
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

    Card(
        colors = CardDefaults.cardColors(Sikt_lyseblå),
        modifier = Modifier.padding(20.dp),
    ) {

        Column(
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item { Sikt_Turer_I_Naerheten("fjelltopp", 1899, 8) }
                item { Sikt_Turer_I_Naerheten("fjelltopp", 1899, 8) }
                item { Sikt_Turer_I_Naerheten("fjelltopp", 1899, 8) }
                item { Sikt_Turer_I_Naerheten("fjelltopp", 1899, 8) }
                item { Sikt_Turer_I_Naerheten("fjelltopp", 1899, 8) }
            }
        }
    }
}
