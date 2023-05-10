package com.example.in2000_prosjekt.ui.components

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.*
import com.example.in2000_prosjekt.ui.database.Favorite
import com.example.in2000_prosjekt.ui.database.FavoriteViewModel
import com.example.in2000_prosjekt.ui.theme.*
import com.example.in2000_prosjekt.ui.uistate.MapUiState

@Composable
fun Sikt_BottomBar(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToInfo: () -> Unit, onNavigateToSettings: () -> Unit, map : Boolean, favorite : Boolean, info : Boolean, settings : Boolean) {

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
                    var iconChosen = R.drawable.outline_favorite
                    if (favorite) {
                        iconChosen = R.drawable.baseline_favorite_24
                    }
                    Icon(
                        painter = painterResource(iconChosen),
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
                IconButton(onClick = { onNavigateToInfo() }) {
                    var iconfarge = Sikt_mørkeblå
                    var iconChosen = R.drawable.outline_info
                    if (info) {
                        iconChosen = R.drawable.baseline_info_filled
                    }
                    Icon(
                        painter = painterResource(iconChosen),
                        "",
                        tint = iconfarge,
                        modifier = Modifier
                            .clip(CircleShape)
                            .padding(5.dp)
                    )
                }
                Text(text = "Info", fontSize = 13.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(75.dp)
            ) {
                IconButton(onClick = { onNavigateToSettings() }) {
                    var iconfarge = Sikt_mørkeblå
                    var iconChosen = R.drawable.outline_settings
                    if (settings) {
                        iconChosen = R.drawable._settings_filled
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
                        painter = painterResource(id = R.drawable.outline_info),
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
fun Sikt_Header(location : String , height: Int, lat: Double, lon: Double, alertinfo: MutableList<AlertInfo>, viewModel: FavoriteViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var openDialog by remember {
            mutableStateOf(false)
        }

        if (alertinfo.size != 0){
            AlertButton( alertinfo.get(0).alertTypeA, alertinfo.get(0).alertLevelA){
                openDialog = true
            }
        }  else {
            // For å fikse at fjelltopp-text blir midtstilt
            IconToggleButton(
                checked = false,
                onCheckedChange = { },
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                    tint = Sikt_lyseblå
                )
            }

        }
        if (openDialog){
            AlertDialog(alertinfo = alertinfo){
                openDialog = false
            }
        }

        if (location.length <= 10) {
            Text(
                modifier = Modifier.weight(2f),
                text = location,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        } else if (location.length <= 15) {
            Text(
                modifier = Modifier.weight(2f),
                text = location,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        } else {
            Text(
                modifier = Modifier.weight(2f),
                text = location,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }

        var checked by remember { mutableStateOf(false) }
        IconToggleButton(
            checked = checked,
            onCheckedChange = { checked = it },
        ) {
            var alreadyFav by remember { mutableStateOf(false)}

            viewModel.findFavorite(lon,lat,location,height)

            Log.d("VIEWSIZE", "${viewModel.searchFavorites.value?.size}")
            alreadyFav = if (viewModel.searchFavorites.value != null) {
                if (viewModel.searchFavorites.value!!.isNotEmpty()) {
                    viewModel.searchFavorites.value!![0].latitude == lat && viewModel.searchFavorites.value!![0].longtitude == lon
                } else {
                    false
                }
            } else {
                false
            }
            Log.d("ALREADYFAV", "$alreadyFav")

            if (checked) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                    tint = Sikt_mørkeblå
                )
                viewModel.addFavorite(Favorite(lon,lat,location,height))
            } else if(alreadyFav && !checked) {
                Icon(
                    painterResource(id = R.drawable.outline_favorite),
                    contentDescription = "Localized description",
                    tint = Sikt_mørkeblå
                )
                viewModel.deleteFavorite(lon,lat)
            } else if (alreadyFav){
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                    tint = Sikt_mørkeblå
                )

            }else {
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
fun Sikt_Favorite_Header(location : String , height: Int, lat: Double, lon: Double, alertinfo: MutableList<AlertInfo>, viewModel: FavoriteViewModel) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var openDialog by remember {
            mutableStateOf(false)
        }
        if (alertinfo.size != 0) {
            AlertButton(alertinfo.get(0).alertTypeA, alertinfo.get(0).alertLevelA) {
                openDialog = true
            }
        } else {
            // For å fikse at fjelltopp-text blir midtstilt
            IconToggleButton(
                checked = false,
                onCheckedChange = { },
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                    tint = Sikt_lyseblå
                )
            }
        }

        if (openDialog) {
            AlertDialog(alertinfo = alertinfo) {
                openDialog = false
            }
        }

        if (location.length <= 10) {
            Text(
                modifier = Modifier.weight(2f),
                text = location,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        } else if (location.length <= 15) {
            Text(
                modifier = Modifier.weight(2f),
                text = location,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        } else {
            Text(
                modifier = Modifier.weight(2f),
                text = location,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }

        var checked by remember { mutableStateOf(true) }

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
                viewModel.deleteFavorite(lon,lat)
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
fun LazyListScope.Sikt_Favorite_card(weatherinfo: MutableList<LocationInfo>, nowcastinfo: MutableList<NowCastInfo>, alertInfo: MutableList<MutableList<AlertInfo>>, favorites: List<Favorite>, viewModel: FavoriteViewModel) {
    //favorites er en mutableList med LocationInfo kan derfor kalle
    // favorite.temperatureL etc.

    Log.d("INFOSIZE", "${weatherinfo.size}")
    Log.d("FAVS", "${favorites.size}")
    Log.d("AlertSIZE", "${alertInfo.size}")

    items(weatherinfo.size) {
            //Log.d("CARD", "STARTER CARD")
            val location = weatherinfo[it]
            val nowcast = nowcastinfo[it]
            val alertInfo = alertInfo[it]
            val name = favorites[it].mountainName
            val height = favorites[it].mountainHeight

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

                    Sikt_Favorite_Header(name,height, favorites[it].latitude, favorites[it].longtitude, alertInfo, viewModel)
                    Sikt_MountainHight(height.toString())
                    illustrasjon(
                        height = height,
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


fun LazyListScope.Sikt_Turer_I_Naerheten(mountains: MutableList<MapUiState.Mountain>, nowCastInfo: NowCastInfo) {

    items(1) {
        mountains.forEach { mountain ->

            val temp = nowCastInfo.temperatureNow

            Card(
                colors = CardDefaults.cardColors(Sikt_bakgrunnblå),
                modifier = Modifier.padding(end = 10.dp)
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
                        text = "${mountain.name}",
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

@Composable
fun DeleteAllButton(viewModel: FavoriteViewModel){
    //Knapp til Instillinger for å slette alle favoritter.
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = Sikt_mørkeblå),
        onClick = {
            viewModel.deleteAll()
        }
    ){
        Text("Slett alle favoritter", color = Sikt_hvit)
    }
}

@Composable
fun illustrasjon(height: Int?, temp: Int, vind: Float, weatherHigh: Float, weatherMid: Float, weatherLow: Float){

    fun getHeightVisuals(height: Int?) : Int {
        return when (height) {
            in 0 .. 500 -> R.drawable.topp_under500
            in 501 .. 1000 -> R.drawable.topp1000to500
            in 1001 .. 1500 -> R.drawable.topp1500to1000
            in 1501 .. 2000 -> R.drawable.topp2000to1500
            else -> R.drawable.topp_over2000
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
                Text(text = getRightSikt(weatherHigh), fontSize = 12.sp, color = Sikt_sort)
            }
            Divider(thickness = 1.dp, color = Sikt_sort, modifier = Modifier.width(100.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(text = "1000 - 2000 m.o.h.", fontSize = 12.sp, color = Sikt_sort)
                Text(text = getRightKm(weatherMid), fontSize = 16.sp, color = Sikt_sort, fontWeight = FontWeight.Bold)
                Text(text = getRightSikt(weatherMid), fontSize = 12.sp, color = Sikt_sort)
            }
            Divider(thickness = 1.dp, color = Sikt_sort, modifier = Modifier.width(100.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(text = "0 - 1000 m.o.h.", fontSize = 12.sp, color = Sikt_sort)
                Text(text = getRightKm(weatherLow), fontSize = 16.sp, color = Sikt_sort, fontWeight = FontWeight.Bold)
                Text(text = getRightSikt(weatherLow), fontSize = 12.sp, color = Sikt_sort)
            }
        }
    }
}

fun LazyListScope.Sikt_InformationCard(rules : Array<String>) {
    items(1) {
        Card(
            modifier = Modifier
                .padding(20.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(Sikt_lyseblå)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_contact_phone),
                    contentDescription = "Phone illustration",
                    tint = Sikt_mørkeblå,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(100.dp),
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    text = "Informasjon",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(50.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Din posisjon:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    //Placeholder for ditt koordinat:
                    text = "59.99436° N, 10,71848° Ø",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Nødnummer:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Medisinsk Nødtelefon:",
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "113",
                        textAlign = TextAlign.End,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Brann:",
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "110",
                        textAlign = TextAlign.End,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Politi:",
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "112",
                        textAlign = TextAlign.End,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Politiets sentralbord:",
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "02800",
                        textAlign = TextAlign.End,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Legevakten:",
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "116117",
                        textAlign = TextAlign.End,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Fjellvettreglene:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    var counter = 1
                    rules.forEach {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 2.dp)
                        ) {
                            Text(
                                text = "$counter. ",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                //modifier = Modifier.padding(start = 20.dp)
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 20.dp),
                                text = it,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                            Spacer(Modifier.height(30.dp))
                            counter++
                        }
                    }
                }
            }
        }
    }
}

fun LazyListScope.Sikt_SettingsCard(viewModel: FavoriteViewModel) {
    items(1) {
        Card(
            modifier = Modifier
                .padding(20.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(Sikt_lyseblå)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Sikt_skyillustasjon()
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    text = "Innstillinger",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(50.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Slett alle favoritter:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                //Legg inn "slett alle favoritter"-knapp
                DeleteAllButton(viewModel)
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Darkmode:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    text = "Kommer snart.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Utviklet av:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Ebba Maja Olsson",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Elisabeth Bårdstu",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Nabil Hassen",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Ola Juul Holm",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Synne Markmanrud",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Thea Hermansen Bakke",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "APIer:",
                    textAlign = TextAlign.Start,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Vi har implementert følgende APIer via meteorologisk insitutt:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp),
                        text = "Locationforecast",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp),
                        text = "Nowcast",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp),
                        text = "MetAlerts",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp),
                        text = "Sunrise",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp),
                        text = "Frost",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "I tillegg er kartet hentet fra:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp),
                        text = "Mapbox",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TestComponent() {
    
    Card(
        colors = CardDefaults.cardColors(Sikt_lyseblå),
        modifier = Modifier.padding(20.dp),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            ///Sikt_Favorite_Header(location, viewModel, alertinfo)
        }
    }
}
