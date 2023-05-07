package com.example.in2000_prosjekt.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.APIViewModel
import com.example.in2000_prosjekt.ui.FrostInfo
import com.example.in2000_prosjekt.ui.data.FrostViewModel
import com.example.in2000_prosjekt.ui.theme.Sikt_hvit
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import com.example.in2000_prosjekt.ui.uistate.FrostReferencetimeUiState
import io.github.boguszpawlowski.composecalendar.CalendarState
import io.github.boguszpawlowski.composecalendar.StaticCalendar
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





// Dette er et tall på dagen 0-30, veriden 0 er dag 1 i måneden, verdien 30 er dag 31 i måneden kl.1945, 07.05
            val datoIArraylista = dayState.date.dayOfMonth.minus(1)

            val sightconditions = frostinfo.sightconditionListofDataforMonth?.get(datoIArraylista)?.observations?.get(0)?.value // tallet skal jo starte med den 28

           val referenceTime11 =frostinfo.sightconditionListofDataforMonth?.get(datoIArraylista)?.referenceTime
            Log.d("Verdi og dag INNI kalenderdagene", "Plassnummer i arrayet"+ datoIArraylista+"Verdi: "+ sightconditions.toString()  + "Dato på dagen:"+ referenceTime11  ) // Er hele datoen for en kalenderdag: 2021-05-21



//-------------------------- attempt med å matche nøyaktig dato fremvvist i øverste høyre hjørnet med verdien fra arraylist
            //merk at vi bare har datoer: for en måned, neste måneds verdier vist i nederste høyre hjørne og forriges månedsverdier verdier er vist i øverste høyre hjørne


            val datoforstfremvistOverstTilVenstre = dayState.date.dayOfMonth
            // Bygg et år
            val year =  dayState.date.year.minus(2).toString()// 2023
            val month = dayState.date.dayOfMonth // Dette er 29. 30 31, også 1,2 ikke 01
            val dayofYear = dayState.date.dayOfYear // er 181, altså den 01.05 er liksom dag 181 av året
            val helelocaldate = dayState.date// er 181, altså den 01.05 er liksom dag 181 av året
            val substringavkalender: String =year+helelocaldate?.toString()?.substring(4,10).toString()

            Log.d("Dette er lokaldateee", substringavkalender ) // Er hele datoen for en kalenderdag: 2021-05-21


            val sightconditionsforAParticularDate = frostinfo.sightconditionListofDataforMonth?.get(datoIArraylista)?.observations?.get(0)?.value // tallet skal jo starte med den 28



            val substringavApikallDatoer =referenceTime11?.toString()?.substring(0,10)
            Log.d("substring", substringavApikallDatoer!! ) // Er hele datoen for en kalenderdag: 2021-05-21

            val sightcondtionsbasertpådato = when {

                (substringavApikallDatoer == substringavkalender  )  -> frostinfo.sightconditionListofDataforMonth?.get(datoIArraylista)?.observations?.get(0)?.value
                else -> "noe galt har skjedd"

            }

             //if (substringavApikallDatoer ==   )

            var weathericon = when {
                (sightconditions == 0.0) -> painterResource(id = R.drawable.klart)
                (0.0 <  sightconditions!! && sightconditions!! <= 3.0 ) -> painterResource(id = R.drawable.lettskyet)
                (3.0 < sightconditions!! && sightconditions!! <= 6.0) -> painterResource(id = R.drawable.delvis_skyet)

                else  -> painterResource(id = R.drawable.skyet)
            }

            Image(painter =  weathericon, contentDescription = "", modifier = Modifier
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
fun Sikt_Historisk_Kalender(   apiViewModel: APIViewModel, frostinfo: FrostInfo,frostViewModel: FrostViewModel) { //
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Sikt_lyseblå), horizontalAlignment = Alignment.CenterHorizontally,)
    {

        val frostUiState by frostViewModel.frostUiState.collectAsState() // Kan man oprette et uiState fra en composable

        var uiStatetoChange by remember { mutableStateOf( false ) }

        val monthState = rememberSaveable(saver = MonthState.Saver()) {
            MonthState(initialMonth = YearMonth.now())
        }// bruker java sin YearMonth ikke bogus

        var calenderstate : CalendarState<EmptySelectionState> = rememberCalendarState(
            monthState = monthState,)
        StaticCalendar( firstDayOfWeek = DayOfWeek.MONDAY, modifier=Modifier.background(Sikt_lyseblå), showAdjacentMonths = false, calendarState =calenderstate, dayContent =  { it -> dayContent(
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



//Denne var apiViewModel.getRefence....
       // apiViewModel.getReferencetimeFrost(referencetime =  datesforfrostsightconditions ) //  //2021-05%2F2021-06

        frostViewModel.getReferencetimeFrost(referencetime=datesforfrostsightconditions)

        Log.d("Periode sendt i api kall:", "Dato: "+ datesforfrostsightconditions + " ApiResponsverdi for datoen: "+ frostinfo.sightconditionListofDataforMonth.toString() ) // Er hele datoen for en kalenderdag: 2021-05-21


        LaunchedEffect(Unit) {
            snapshotFlow { datesforfrostsightconditions } // kanskje feil det som står her
                .collect { currentperiod ->
                    // viewModel.doSomething()
                    // Kommentar: søndag 07.05: kl.1900:Etter forslag Elisabeth: Det at vi bruker launched effect
                    // gjør IKKE at variablen som blir sendt oppdaterer seg med nytt dato
                   // Derfor kommenteres den ut i launched effect, kl 1923
                    //frostViewModel.getReferencetimeFrost(referencetime=datesforfrostsightconditions)


                }
        }

        /*
        if (monthState.){ // når monthstate endrer seg så endre uistate, når uistate endre seg, ta med endringen som er datoen // åja mkan kl-21-31 kan man bare sende monthstate gjennom tro
            uiStatetoChange = true
            frostViewModel.getReferencetimeFrost(datesforfrostsightconditions)

            }

         */
        /*   LaunchedEffect(Unit) {
               snapshotFlow { monthState.currentMonth }
                   .collect { currentMonth ->
                       // viewModel.doSomething()
                   }
           }*/



// når vi har gjort apikallet så sa emil lagre det i en ArrayList med datoer, 0605. kl. 14.48

       val arraylistwithSightValues = frostinfo.sightconditionListofDataforMonth
        // size skal være sånn 30 - 31 stk elementer, svarer til at api repsos for en måned gitt i apikallet
        Log.d("Info om api repsons Json Array watergate","Størrelse: List<DataFrost>?: "+ frostinfo.sightconditionListofDataforMonth?.size )


        Log.d("Antall observcation=Skalværelik30-31","Antallobservations: "+ frostinfo.sightconditionListofDataforMonth?.get(0)?.observations?.get(0)?.timeSeriesId.toString())
        Log.d("DAg 3, 03.01, dato og value  ","Valie: "+ frostinfo.sightconditionListofDataforMonth?.get(2)?.observations?.get(0)?.value.toString() +"Dag"+ frostinfo.sightconditionListofDataforMonth?.get(0)?.referenceTime.toString())




        Image(painter = painterResource(id = R.drawable.klart), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ modifier = Modifier.size(30.dp)/*.fillMaxWidth(0.7f)*/)



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