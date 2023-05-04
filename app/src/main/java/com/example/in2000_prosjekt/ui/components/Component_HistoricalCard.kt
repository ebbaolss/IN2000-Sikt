package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.APIViewModel
import com.example.in2000_prosjekt.ui.FrostInfo
import com.example.in2000_prosjekt.ui.theme.Sikt_hvit
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import com.example.in2000_prosjekt.ui.theme.Sikt_mørkeblå
import io.github.boguszpawlowski.composecalendar.CalendarState
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import io.github.boguszpawlowski.composecalendar.day.Day
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.day.NonSelectableDayState
import io.github.boguszpawlowski.composecalendar.rememberCalendarState
import io.github.boguszpawlowski.composecalendar.selection.EmptySelectionState
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun Sikt_Historisk_KalenderGammelogNotMine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Sikt_hvit),
    ) {
        Text(text = "Kalender")
    }
}






//I vår composable funksjon som generer kalenderen vår (se neste Composable-funskjon: StaticCalender() ) så kan man bestemme innholdet til de ulike bestanddelene av en kalender, slikt som tittel, plassering av de ulike trykkbare komponetene i kalenderen, om den er scrollbar etc.
//Dette er funksjonen som bestemmer dagsinnholdet i hver dag kalenderen StaticCalender()
@Composable
fun dayContent(dayState: NonSelectableDayState , frostinfo: FrostInfo,  apiViewModel: APIViewModel ) : MutableList<LocalDate> {

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

            //  date: YYYY-MM-DD "2021-05-12" // den monthValue-Trenger ikke å ha 0-symbolet forran seg: 05 og 5 funker
            val year = dayState.date.year -2 // dette er året 2021

            val date= year.toString()+"-"+dayState.date.monthValue.toString() +"-"+ dayState.date.dayOfMonth.toString()

            apiViewModel.getReferencetimeFrost(referencetime =  date )

            Log.d("frostinfo:SightConditions(FullDate: YYYY-MM-DD)", frostinfo.sightcondition.toString() ) // Er hele datoen for en kalenderdag: 2021-05-21


            // Picks icon to be shown depending on the sight / sikt conditions: The lower the APi value the more clear the sky is(the better the conditions)
            // frostinfo.sightcondition is on a scale from: [0-8]: 0 being Great sight / sikt conditions, 8 being Poor sight / sikt conditions,
            var weathericon = when {

                (frostinfo.sightcondition == 0)  ->  painterResource(id = R.drawable.klart)
                (0 < frostinfo.sightcondition && frostinfo.sightcondition < 3) -> painterResource(id = R.drawable.lettskyet)

                (3 < frostinfo.sightcondition && frostinfo.sightcondition < 6) -> painterResource(id = R.drawable.delvis_skyet)
                else ->painterResource(id = R.drawable.skyet) // Denne else'en representerer alle dager hvor
            }



            Log.d("FullDate: YYYY-MM-DD", date) // Er hele datoen for en kalenderdag: 2021-05-21
/*
            Log.d("dateNumber", dayState.date.toString()) // Er hele datoen for en kalenderdag: 2023-05-21
            Log.d("isCurrentDay", dayState.isCurrentDay.toString()) // Er hele datoen for en kalenderdag: 2023-05-21
            Log.d("isFromCurrentMonth",  dayState.isFromCurrentMonth.toString()) // Er hele datoen for en kalenderdag: 2023-05-21
            Log.d("isFromCurrentMonth",  dayState.selectionState.toString()) // Er hele datoen for en kalenderdag: 2023-05-21

 */



            Image(painter =  weathericon  ,//painterResource(id =  R.drawable.klart),
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



//Dette er en Composable funksjon som generer en kalender med et dagsinnhold bestemt av funksjonen dayContent
//En StaticCalender er en kalender som kun presenterer en bruker for info tilknyttet siktforholdene hver dag i måneden. Biblioteket brukt for å generere denne kalenderen har andre typer kalendere (slikt som ukeskalendere mm.), men i henhold til kravspesifikasjonen så anså vi en StaticCalender som mest passende.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sikt_Historisk_Kalender(   apiViewModel: APIViewModel, frostinfo: FrostInfo ) { //

    //val historicCardUiState by APIViewModel.appUiState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Sikt_lyseblå),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){


        var calenderstate : CalendarState<EmptySelectionState> = rememberCalendarState()


        StaticCalendar( firstDayOfWeek = DayOfWeek.MONDAY, modifier=Modifier.background(Sikt_lyseblå), calendarState =calenderstate, dayContent =  { it -> dayContent(
            dayState =  it , frostinfo = frostinfo,  apiViewModel)

        }
        )

        //APIViewModel.getFrost( referencetimetest = ( dayContent()   )


        Text(text= "Picture description:")


        Text(text= "Klart:")
        Image(painter = painterResource(id = R.drawable.klart), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/)



        Text(text= "Lettskyet:")
        Image(painter = painterResource(id = R.drawable.lettskyet), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/)


        Text(text= "Delvis skyet:")
        Image(painter = painterResource(id = R.drawable.delvis_skyet), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/)

        Text(text= "Skyet:")
        Image(painter = painterResource(id = R.drawable.skyet), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/)



        var day : Day

        var dagen: Daygen = Daygen() // kl 15.33 også


        //var kalenderdager= dayContent(dayState = DayState<EmptySelectionState>(selectionState = calenderstate.selectionState, day = day /*Day(calenderstate.monthState.currentMonth.atDay(1))*/ ))



        //Log.d("Halladagdate", dayContent(dayState = calenderstate) .date.toString()) // Er hele datoen for en kalenderdag: 2023-05-21


        //Log.d("Halladagdate", dayContent(dayState = calenderstate) .date.toString()) // Er hele datoen for en kalenderdag: 2023-05-21


        // var datoertilgjengelig: List<LocalDate> = calenderstate.monthState.currentMonth


        //var datoertilgjengelig = calenderstate.monthState.currentMonth.month


        //Log.d("calenderstate.monthState.currentMonth.month", calenderstate.monthState.currentMonth.month.toString()) // Er hele datoen for en kalenderdag: 2023-05-21

    }

}

class Daygen : Day {
    public override val date: LocalDate
        get() {
            TODO()
        }
    public override val isCurrentDay: Boolean
        get() {
            TODO()
        }
    public override val isFromCurrentMonth: Boolean
        get() {
            TODO()
        }

    companion object{
        fun Daygen (date: LocalDate, isCurrentday: Boolean, isFromCurrentMonth: Boolean)  {

        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sikt_HistoriskCard(){

    Card(
        colors = CardDefaults.cardColors(Sikt_lyseblå),
        modifier = Modifier.padding(20.dp),
    ) {

        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            //Sikt_Header("Fjelltopp")
            Sikt_MountainHight("1800")
            Spacer(modifier = Modifier.size(20.dp))
            //illustrasjon(1469, -11f, 5f, "skyet", "delvisskyet", "klart")
            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "Historisk Kalender: ", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.size(10.dp))
           // Sikt_Historisk_Kalender() kommentert ut kl 18.45 . Vi fikk først i dag tirsdag den 02.05 til det å sende info på tvers av Mapbox Screen gjennom viewmodel
            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "Topper i nærheten: ", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.size(10.dp))
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

/*
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
fun HistoricalTest() {

    Sikt_Historisk_Kalender()

}*/