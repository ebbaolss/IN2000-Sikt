package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.in2000_prosjekt.ui.theme.Sikt_white

@Composable
fun Sikt_Historisk_Kalender() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Sikt_white),
    ) {
        Text(text = "Kalender")
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
/*@OptIn(ExperimentalMaterial3Api::class)
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
*/

/*
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
            Sikt_Historisk_Kalender()
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
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
fun HistoricalTest() {

    //Sikt_HistoriskCard()

}