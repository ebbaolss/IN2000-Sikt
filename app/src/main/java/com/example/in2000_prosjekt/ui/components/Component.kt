package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.*
import com.example.in2000_prosjekt.database.Favorite
import com.example.in2000_prosjekt.database.FavoriteViewModel
import com.example.in2000_prosjekt.ui.theme.*
import com.example.in2000_prosjekt.ui.uistate.MapUiState

@Composable
fun Sikt_BottomBar(onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToInfo: () -> Unit, onNavigateToSettings: () -> Unit, map : Boolean, favorite : Boolean, info : Boolean, settings : Boolean) {

    BottomAppBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)),
        containerColor = Sikt_white,

        ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Column (horizontalAlignment = Alignment.CenterHorizontally,
            ){
                IconButton(onClick = { onNavigateToMap() }) {
                    val iconfarge = Sikt_darkblue
                    var iconChosen = R.drawable.outline_place_outline
                    if (map) {
                        iconChosen = R.drawable.baseline_place_filled
                    }
                    Icon(
                        painter = painterResource(iconChosen),
                        contentDescription = "Utforsk knapp",
                        tint = iconfarge,
                        modifier = Modifier
                            .clip(CircleShape)
                            .padding(5.dp))
                }
                Text(text = "   Utforsk   ", fontSize = 13.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                IconButton(onClick = { onNavigateToFav() }) {
                    val iconfarge = Sikt_darkblue
                    var iconChosen = R.drawable.outline_favorite
                    if (favorite) {
                        iconChosen = R.drawable.baseline_favorite_24
                    }
                    Icon(
                        painter = painterResource(iconChosen),
                        contentDescription = "Favoritter knapp",
                        tint = iconfarge,
                        modifier = Modifier
                            .clip(CircleShape)
                            .padding(5.dp)
                    )
                }
                Text(text = " Favoritter ", fontSize = 13.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                IconButton(onClick = { onNavigateToInfo() }) {
                    val iconfarge = Sikt_darkblue
                    var iconChosen = R.drawable.outline_info
                    if (info) {
                        iconChosen = R.drawable.baseline_info_filled
                    }
                    Icon(
                        painter = painterResource(iconChosen),
                        "Info knapp",
                        tint = iconfarge,
                        modifier = Modifier
                            .clip(CircleShape)
                            .padding(5.dp)
                    )
                }
                Text(text = "    Info    ", fontSize = 13.sp) //12
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                IconButton(onClick = { onNavigateToSettings() }) {
                    val iconfarge = Sikt_darkblue
                    var iconChosen = R.drawable.outline_settings
                    if (settings) {
                        iconChosen = R.drawable._settings_filled
                    }
                    Icon(
                        painterResource(iconChosen),
                        "Instillinger knapp",
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

//ADD: boolean for når den kommer fra favorite screen.
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
            AlertButton( alertinfo[0].alertTypeA, alertinfo[0].alertLevelA){
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
                    contentDescription = "Marker favoritt knapp",
                    tint = Sikt_lightblue
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

            if (alreadyFav){
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favoritt",
                    tint = Sikt_darkblue
                )
            } else if(!checked && alreadyFav) {
                Icon(
                    painterResource(id = R.drawable.outline_favorite),
                    contentDescription = "Ikke en favoritt",
                    tint = Sikt_darkblue
                )
                viewModel.deleteUpdate(lon,lat)
            } else if (checked) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favoritt",
                    tint = Sikt_darkblue
                )
                viewModel.addFavorite(Favorite(lon,lat,location,height))
            } else {
                Icon(
                    painterResource(id = R.drawable.outline_favorite),
                    contentDescription = "Ikke en favoritt",
                    tint = Sikt_darkblue
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
            AlertButton(alertinfo[0].alertTypeA, alertinfo[0].alertLevelA) {
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
                    contentDescription = "Marker favoritt knapp",
                    tint = Sikt_lightblue
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
                    contentDescription = "Favoritt",
                    tint = Sikt_darkblue
                )
            } else {
                Icon(
                    painterResource(id = R.drawable.outline_favorite),
                    contentDescription = "Ikke en favoritt",
                    tint = Sikt_darkblue
                )
                viewModel.deleteUpdate(lon,lat)
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
        contentDescription = "Sky illustrasjon",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
fun LazyListScope.siktFavoriteCard(weatherinfo: MutableList<LocationInfo>, nowcastinfo: MutableList<NowCastInfo>, alertInfo: MutableList<MutableList<AlertInfo>>, favorites: List<Favorite>, viewModel: FavoriteViewModel, onNavigateToMap: () -> Unit, onNavigateToFav: () -> Unit, onNavigateToInfo: () -> Unit, onNavigateToSettings: () -> Unit) {
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
        val lon = favorites[it].longtitude
        val lat = favorites[it].latitude
        val mount = MapUiState.Mountain(name, lat.toString(), lon.toString(), height)


        var popupControl by remember { mutableStateOf(false) }

        if (popupControl) {
            Popup(alignment = Alignment.Center) {

                Scaffold(bottomBar = { Sikt_BottomBar(
                    onNavigateToMap,
                    onNavigateToFav,
                    onNavigateToInfo,
                    onNavigateToSettings,
                    map = false,
                    favorite = true,
                    info = false,
                    settings = false
                )}) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .paint(
                            painterResource(id = R.drawable.map_backround),
                            contentScale = ContentScale.FillBounds
                        ).clickable { popupControl = false }) {
                        LazyColumn(
                            modifier = Modifier
                                //.fillMaxSize()
                                .padding(top = 0.dp, bottom = 70.dp, start = 20.dp, end = 20.dp)

                        ) {
                            // Må legge inn listen over fjelltopper i nærheten:
                            siktLocationcard(
                                mount, location, nowcast, alertInfo, viewModel
                            )
                        }
                    }
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clickable { popupControl = true },
            colors = CardDefaults.cardColors(Sikt_lightblue)
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
                Illustrasjon(
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

@Composable
fun DeleteAllButton(viewModel: FavoriteViewModel){
    //Knapp til Instillinger for å slette alle favoritter.

    Button(colors = ButtonDefaults.buttonColors(Sikt_darkblue),
        onClick = {
            viewModel.deleteAll()
        }
    ){
        Text("Slett alle favoritter", color = Sikt_white)
    }
}

@Composable
fun Illustrasjon(height: Int?, temp: Int, vind: Float, weatherHigh: Float, weatherMid: Float, weatherLow: Float){

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
            contentDescription = "Bakgrunn",
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = getHeightVisuals(height)),
            contentDescription = "Høyde visulisering",
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = getHighClouds(weatherHigh)),
            contentDescription = "Høyt skylag",
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = getMidClouds(weatherMid)),
            contentDescription = "Middels skylag",
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = getLowClouds(weatherLow)),
            contentDescription = "Lavt skylag",
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
                contentDescription = "Vind ikon",
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

fun LazyListScope.siktInformationCard(rules : Array<String>) {
    items(1) {
        Card(
            modifier = Modifier
                .padding(20.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(Sikt_lightblue)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_contact_phone),
                    contentDescription = "Telefon illustrasjon",
                    tint = Sikt_darkblue,
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
                    text = "Nødnummer:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
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
                Spacer(modifier = Modifier.height(10.dp))
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
                Spacer(modifier = Modifier.size(30.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Fjellvettreglene:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(10.dp))
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

fun LazyListScope.siktSettingsCard(viewModel: FavoriteViewModel) {
    items(1) {
        Card(
            modifier = Modifier
                .padding(20.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(Sikt_lightblue)
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
                    text = "Kommer snart:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    text = "Darkmode",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    text = "Historisk data",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    text = "Topper i nærheten",
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
