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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
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
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.rememberCalendarState
import io.github.boguszpawlowski.composecalendar.selection.EmptySelectionState
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

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
    Card(modifier = Modifier
        .aspectRatio(1f)
        .padding(2.dp), border =  BorderStroke(1.dp, Sikt_hvit))
    {
        Column(modifier = Modifier.fillMaxWidth()) {

            Text(text = dayState.date.dayOfMonth.toString()+".", modifier=Modifier.padding(start = 2.4.dp))


            val year = dayState.date.year -2
            //  date: YYYY-MM-DD "2021-05-12" // monthValue-Trenger ikke å ha 0-symbolet forran seg: 05 og 5 funker
            val date= year.toString()+"-"+dayState.date.monthValue.toString() +"-"+ dayState.date.dayOfMonth.toString()

            //Api kallet
            apiViewModel.getReferencetimeFrost(referencetime =  date )

            Log.d("Dato: også Api respons verdi (frostinfo:SightConditions)", "Dato: "+ date + " ApiResponsverdi for datoen: "+ frostinfo.sightcondition.toString() ) // Er hele datoen for en kalenderdag: 2021-05-21


            var weathericon = painterResource(id = R.drawable.skyet)
            if (frostinfo.sightcondition == 0) weathericon = painterResource(id = R.drawable.klart)
            else if (frostinfo.sightcondition < 3) weathericon = painterResource(id = R.drawable.lettskyet)
            else if (frostinfo.sightcondition  > 100) weathericon = painterResource(id = R.drawable.delvis_skyet)


            Image(painter =  weathericon, contentDescription = "", modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(2f)
                .wrapContentSize(Alignment.Center)
                .size(30.dp))


/*
            //val RememberState = dayState.value // går ikke
            //val RememberState3 = dayState.selectionState.value // går ikke
            // linjene under er et forsøk på å bruke DayState sin recomposition og remember, torsdag 04.05, kl.2055
            val testRememberForHverdag by remember { mutableStateOf(dayState) }
            val rememberyear = testRememberForHverdag.date.year -2
            val rememberdate= rememberyear.toString()+"-"+testRememberForHverdag.date.monthValue.toString() +"-"+ testRememberForHverdag.date.dayOfMonth.toString()
 */

            // apikall på remembered Daystate variable: testRememberForHverdag torsdag 04.05, kl.2055

            /*  LaunchedEffect(testRememberForHverdag.date.dayOfMonth){
                  apiViewModel.getReferencetimeFrost(referencetime =  rememberdate )
              }

             */

            // Log.d("Dato: også Api respons verdi (frostinfo:SightConditions)", "Dato: "+ rememberdate + " ApiResponsverdi for datoen: "+ frostinfo.sightcondition.toString() ) // Er hele datoen for en kalenderdag: 2021-05-21

            // Picks icon to be shown depending on the sight / sikt conditions: The lower the APi value the more clear the sky is(the better the conditions)
            // frostinfo.sightcondition is on a scale from: [0-8]: 0 being Great sight / sikt conditions, 8 being Poor sight / sikt conditions,

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
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Sikt_lyseblå), horizontalAlignment = Alignment.CenterHorizontally,)
    {

        val monthState = rememberSaveable(saver = MonthState.Saver()) {
            MonthState(initialMonth = YearMonth.now()) }// bruk java sin YearMonth ikke bogus
        var calenderstate : CalendarState<EmptySelectionState> = rememberCalendarState(
            monthState = monthState,)
        StaticCalendar( firstDayOfWeek = DayOfWeek.MONDAY, modifier=Modifier.background(Sikt_lyseblå), calendarState =calenderstate, dayContent =  { it -> dayContent(
            dayState =  it , frostinfo = frostinfo,  apiViewModel) })
        // monthState er en funksjon av MonthState fra bogus, som sin .currentMonth er av typen YearMonth fra java biblioteket
        Text(text= "Nr1: monthState.currentMonth: "+monthState.currentMonth.toString()) // 2023-05
        Text(text= "Nr2: monthState.currentMonth.monthValue: "+monthState.currentMonth.monthValue.toString()) // 05
        val year =  monthState.currentMonth.year.toString() // 2023
        val year21 =  monthState.currentMonth.year.minus(2).toString() // 2021
        val year2021 =  monthState.currentMonth.minusYears(2).toString() //2021-05
        val monthweneedresultsfrom =  monthState.currentMonth.monthValue.toString() // 05 BEginning period we want api call resutlts from, value dynamically ajusts to current month being shown on the calender
        val nextmonthweneedresultsfrom =  monthState.currentMonth.monthValue.plus(1).toString()// 06 Ending period we want api call resutlts from, the value dynamically ajusts to next month being shown on the calender

        //2021-05%2F2021-06
        val datesforfrostsightconditions = year21+"-"+monthweneedresultsfrom+"%2F"+year21+"-"+nextmonthweneedresultsfrom
        Text(text= "Nr3: monthState.year: " + year.toString())
        Text(text= "Nr4: Apicall verdi: " + datesforfrostsightconditions)
        // test med aaaalle datoer 60 dager


     /*   LaunchedEffect(Unit) {
            snapshotFlow { monthState.currentMonth }
                .collect { currentMonth ->
                    // viewModel.doSomething()
                }
        }*/

        LaunchedEffect(Unit) {
            snapshotFlow { datesforfrostsightconditions }
                .collect { currentperiod ->
                    // viewModel.doSomething()

                    apiViewModel.getReferencetimeFrost(referencetime =  currentperiod )
                }
        }


        /*
Måneden: Måned som ønskes lagret
CalenderState(Måneden)
StaticCalender(CalenderState)
LaunchedEffect(Unit)
         */
        Text(text= "Picture description:")
        Text(text= "Klart:")
        Image(painter = painterResource(id = R.drawable.klart), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/)
        Text(text= "Lettskyet:")
        Image(painter = painterResource(id = R.drawable.lettskyet), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/)
        Text(text= "Delvis skyet:")
        Image(painter = painterResource(id = R.drawable.delvis_skyet), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/)
        Text(text= "Skyet:")
        Image(painter = painterResource(id = R.drawable.skyet), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/)



    }

}

/*
Merk deg rekkefølgen i denne: https://github.com/boguszpawlowski/ComposeCalendar/issues/84


Måneden: Måned som ønskes lagret
CalenderState(Måneden)
StaticCalender(CalenderState)
LaunchedEffect(Unit)

val monthState = rememberSaveable(saver = MonthState.Saver()) {
    MonthState(initialMonth = YearMonth.now())
  }

  val calendarState = rememberCalendarState(
    monthState = monthState,
  )

  StaticCalendar(
    calendarState = calendarState,
  )

  LaunchedEffect(Unit) {
    snapshotFlow { monthState.currentMonth }
      .collect { currentMonth ->
        // viewModel.doSomething()
      }
  }


*/



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