package com.example.in2000_prosjekt.ui.components

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.in2000_prosjekt.R
import com.example.in2000_prosjekt.ui.APIViewModel
import com.example.in2000_prosjekt.ui.FrostInfo
import com.example.in2000_prosjekt.ui.theme.Sikt_hvit
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import io.github.boguszpawlowski.composecalendar.CalendarState
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import io.github.boguszpawlowski.composecalendar.day.NonSelectableDayState
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.rememberCalendarState
import io.github.boguszpawlowski.composecalendar.selection.EmptySelectionState
import java.time.DayOfWeek
import java.time.LocalDate
import kotlin.time.Duration.Companion.days

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
@OptIn(ExperimentalStdlibApi::class)
@Composable
fun dayContent(dayState: NonSelectableDayState , frostinfo: FrostInfo, monthState:MonthState  ) : MutableList<LocalDate> {
    var alledager = mutableListOf<LocalDate>()
    Card(modifier = Modifier
        .aspectRatio(1f)
        .padding(2.dp), border =  BorderStroke(1.dp, Sikt_hvit))
    {
        Column(modifier = Modifier.fillMaxWidth()) {

            Text(text = dayState.date.dayOfMonth.toString()+".", modifier=Modifier.padding(start = 2.4.dp))


            // Dette er et tall på datoene 0-30, veriden 0 er dag 1 i måneden, verdien 30 er dag 31 i måneden kl.1945, 07.05

            val datoIArraylista = dayState.date.dayOfMonth.minus(1)  // når vi er på dato 01, så hent plass 0 i arrayList med api kall resultater
            // måneden mai: 0-30 elementer


            //Log.d("Sjekke", datoIArraylista.toString())

            val datesightcondition = monthState.currentMonth.month.value.days.toString()
            //   Log.d("Teste MonthYear og DayState", datesightcondition) // prøv å finne et array av alle dagene i monthState

// Nå kl.17.59 dato: 10.05

            //val range = dayState.date.range(monthState.currentMonth)
            val r1 = dayState.date.withMonth(monthState.currentMonth.monthValue).dayOfMonth
            val r2 = dayState.date.withMonth(monthState.currentMonth.monthValue).dayOfMonth
            //val r3 = dayState.date.range()
            val r4 = dayState.date.rangeTo(dayState.date.withMonth(monthState.currentMonth.monthValue)) //  2023-05-01..2023-05-01
            val r6 = dayState.date.rangeUntil(monthState.currentMonth.atEndOfMonth()) // 2023-05-30..<2023-05-31
            //val temporalrange = LocalDate of(monthState.currentMonth.year,monthState.currentMonth.year,)
            //  Log.d("Teste MonthYear og DayState", dayState.date.ValueRange(monthState.currentMonth.monthValue)) // prøv å finne et array av alle dagene i monthState
            //Log.d("Teste MonthYear og DayState", r4.toString()) // prøv å finne et array av alle dagene i monthState

            val datoIArraylistaee = dayState.date.dayOfMonth.rangeTo(monthState.currentMonth.lengthOfMonth())
            val datoIArraylistsaee = dayState.date.dayOfMonth.coerceAtMost(monthState.currentMonth.lengthOfMonth()) // funka ikke ga fortsatt 2 mnd
            val dae = dayState.date.dayOfMonth
            val dae3 = dayState.date.dayOfMonth..monthState.currentMonth.atEndOfMonth().dayOfMonth
            val makslengde = IntArray (monthState.currentMonth.lengthOfMonth() )

            var i by remember { mutableStateOf(0)}

            val dateswitinmonth  = when {
                (dayState.date.month == monthState.currentMonth.month  ) -> dayState.date.dayOfMonth.minus(1)

                else  -> dayState.date.dayOfMonth.minus(1)
            }



            if (dayState.date.month == monthState.currentMonth.month  ) {


                val sightconditions = frostinfo.sightconditionListofDataforMonth?.get(dayState.date.dayOfMonth.minus(1))?.observations?.get(0)?.value // tallet skal jo starte med den 28

                //Log.d("BareDAtoff", makslengde[i].toString()) // prøv å finne et array av alle dagene i monthState
                //  Log.d("BareDAtoff", dateswitinmonth.toString()) // prøv å finne et array av alle dagene i monthState
                Log.d("BareDAtoff", dayState.isFromCurrentMonth.toString()) // prøv å finne et array av alle dagene i monthState


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

            } else Unit





            // dette funka ikke ahhahahaha
            //Log.d("iFØRINKREMENT", i.toString()) // prøv å finne et array av alle dagene i monthState
            // i++
            // Log.d("iETTERINKREMENT", i.toString()) // prøv å finne et array av alle dagene i monthState




            //---------------------------------------------------------kl.17.51, onsdag 10.05
            //  val referenceTime11 =frostinfo.sightconditionListofDataforMonth?.get(datoIArraylista)?.referenceTime

//-------------------------- attempt med å matche nøyaktig dato fremvvist i øverste høyre hjørnet med verdien fra arraylist
            //merk at vi bare har datoer: for en måned, neste måneds verdier vist i nederste høyre hjørne og forriges månedsverdier verdier er vist i øverste høyre hjørne


            val datoforstfremvistOverstTilVenstre = dayState.date.dayOfMonth
            // Bygg et år
            val year =  dayState.date.year.minus(2).toString()// 2023
            val month = dayState.date.dayOfMonth // Dette er 29. 30 31, også 1,2 ikke 01
            val dayofYear = dayState.date.dayOfYear // er 181, altså den 01.05 er liksom dag 181 av året
            val helelocaldate = dayState.date// er 181, altså den 01.05 er liksom dag 181 av året
            val substringavkalender: String =year+helelocaldate?.toString()?.substring(4,10).toString()




//            val sightconditionsforAParticularDate = frostinfo.sightconditionListofDataforMonth?.get(datoIArraylista)?.observations?.get(0)?.value // tallet skal jo starte med den 28




            //  val substringavApikallDatoer =referenceTime11?.toString()?.substring(0,10)
            // Log.d("substring", substringavApikallDatoer!! ) // Er hele datoen for en kalenderdag: 2021-05-21

            val sightcondtionsbasertpådato = when {

                //   (substringavApikallDatoer == substringavkalender  )  -> frostinfo.sightconditionListofDataforMonth?.get(datoIArraylista)?.observations?.get(0)?.value
                else -> "noe galt har skjedd"

            }
            // Log.d("sdfasdfgadgfasdfgsdfgsdf", "Verdi"+ sightcondtionsbasertpådato + " ApiResponsverdi for datoen: "+ frostinfo.sightconditionListofDataforMonth.toString() ) // Er hele datoen for en kalenderdag: 2021-05-21
            //  Log.d("substringavApikallDatoer", "Dato å basere apikall på"+ substringavApikallDatoer ) // Er hele datoen for en kalenderdag: 2021-05-21



            //if (substringavApikallDatoer ==   )


            alledager.add(dayState.date) // generer en liste med innholdet i kalenderen
        }
    }


    return alledager
}





//Dette er en Composable funksjon som generer en kalender med et dagsinnhold bestemt av funksjonen dayContent
//En StaticCalender er en kalender som kun presenterer en bruker for info tilknyttet siktforholdene hver dag i måneden. Biblioteket brukt for å generere denne kalenderen har andre typer kalendere (slikt som ukeskalendere mm.), men i henhold til kravspesifikasjonen så anså vi en StaticCalender som mest passende.
@OptIn(ExperimentalMaterial3Api::class)


@Composable
fun Sikt_Historisk_Kalender(   frostinfo: FrostInfo, monthState: MonthState) { //

   //val scrolstate = rememberScrollState()

    Column(modifier = Modifier//.fillMaxHeight()
        // pixel 6 så fillmax size helt tullete ut, endret til height, på torsdag 11.05, kl.1954
        .background(Sikt_lyseblå)/*.verticalScroll(scrolstate )*/)
    {

        //val frostUiState by frostViewModel.frostUiState.collectAsState() // Kan man oprette et uiState fra en composable

        var uiStatetoChange by remember { mutableStateOf( false ) }


        var calenderstate : CalendarState<EmptySelectionState> = rememberCalendarState(
            monthState = monthState,)



        Text(text= "Historisk væroversikt", modifier=Modifier.fillMaxWidth(), fontSize = 23.sp,  textAlign = TextAlign.Center,fontWeight = FontWeight.Bold) // alltid ha wrapcontentWidth som siste funksjono på modifier, atlså før size()

        Box (/*modifier=Modifier.offset(x = -columnPadding)*/) {
            StaticCalendar( firstDayOfWeek = DayOfWeek.MONDAY, modifier=Modifier.fillMaxWidth().wrapContentWidth( Alignment.CenterHorizontally,).background(Color.Green), showAdjacentMonths = false, calendarState =calenderstate, dayContent =  { it -> dayContent(
                dayState =  it , frostinfo = frostinfo, monthState  ) })

        }

        // monthState er en funksjon av MonthState fra bogus, som sin .currentMonth er av typen YearMonth fra java biblioteket


        val year =  monthState.currentMonth.year.toString() // 2023
        val year21 =  monthState.currentMonth.year.minus(2).toString() // 2021
        val year2021 =  monthState.currentMonth.minusYears(2).toString() //2021-05
        val monthweneedresultsfrom =  monthState.currentMonth.monthValue.toString() // 05 BEginning period we want api call resutlts from, value dynamically ajusts to current month being shown on the calender
        val nextmonthweneedresultsfrom =  monthState.currentMonth.monthValue.plus(1).toString()// 06 Ending period we want api call resutlts from, the value dynamically ajusts to next month being shown on the calender

        val datesforfrostsightconditions = year21+"-"+monthweneedresultsfrom+"%2F"+year21+"-"+nextmonthweneedresultsfrom



        Text(text= "Beskrivelse",  textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().wrapContentWidth( Alignment.CenterHorizontally),fontSize = 12.sp,)

     //   Spacer(modifier=Modifier.height(6.dp))

        Text(text= "Klart: Stor sannsynlighet for svært gode siktforhold", textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth().wrapContentWidth( Alignment.CenterHorizontally),fontSize = 11.sp,)
        Image(painter = painterResource(id = R.drawable.klart), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ alignment= Alignment.Center, modifier = Modifier.fillMaxWidth().wrapContentWidth( Alignment.CenterHorizontally,).size(25.dp) )
        //     Spacer(modifier=Modifier.height(2.dp))

        Text(text= "Lettskyet: Stor sannsynlighet for gode siktforhold", textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth().wrapContentWidth( Alignment.CenterHorizontally),fontSize = 11.4.sp,)
        Image(painter = painterResource(id = R.drawable.lettskyet), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ alignment= Alignment.Center,modifier = Modifier.fillMaxWidth().wrapContentWidth( Alignment.CenterHorizontally,).size(25.dp)/*.fillMaxWidth(0.7f)*/)
        //    Spacer(modifier=Modifier.height(2.dp))

        Text(text= "Delvis skyet: Stor sannsynlighet for mindre gode siktforhold", textAlign = TextAlign.Center ,modifier = Modifier.fillMaxWidth().wrapContentWidth( Alignment.CenterHorizontally)/*modifier = Modifier.fillMaxWidth().wrapContentWidth( Alignment.CenterHorizontally,)*/,fontSize = 10.9.sp,)
        Image(painter = painterResource(id = R.drawable.delvis_skyet), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ alignment= Alignment.Center,modifier = Modifier.fillMaxWidth().wrapContentWidth( Alignment.CenterHorizontally,).size(25.dp)/*.fillMaxWidth(0.7f)*/)
        //    Spacer(modifier=Modifier.height(2.dp))
        Text(text= "Skyet: Stor sannsynlighet for svært dårlige siktforhold",modifier = Modifier.fillMaxWidth().wrapContentWidth( Alignment.CenterHorizontally), textAlign = TextAlign.Center,fontSize = 11.sp,)
        Image(painter = painterResource(id = R.drawable.skyet), contentDescription = "test med fare i hver", /*contentScale = ContentScale.FillWidth,*/ alignment= Alignment.Center,modifier = Modifier.fillMaxWidth().wrapContentWidth( Alignment.CenterHorizontally,).size(25.dp)/*.fillMaxWidth(0.7f)*/)



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
