package com.example.in2000_prosjekt.ui.screens

import com.example.in2000_prosjekt.ui.FrostViewModel


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.in2000_prosjekt.ui.data.Frost_API_Respons


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun FrostScreen (responseViewModel: FrostViewModel = viewModel()){


    val UIState by responseViewModel.responsUiState.collectAsState()



    var text by remember  {mutableStateOf(value=("")) }
    var focusClear = LocalFocusManager.current





    Column (modifier= Modifier
        .fillMaxHeight()
        .wrapContentHeight(Alignment.CenterVertically)) {

        var isClicked by remember {mutableStateOf(false) }


        Text (modifier=Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally),text="Svaret på forespørselen kommer som et card under søkefeltet")

        OutlinedTextField(modifier=Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally),
            value = text,
            onValueChange = { text = it},
            label = { Text("Skriv sted/by: ")}
        )


        Button(modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
            onClick = {

                responseViewModel.settSource(text) // Angir SN sensor (lokalisasjon til et api kall)

                isClicked= true



                Log.d("tekst", text)

                text= "" // gjør søkefeltet hvit

                focusClear.clearFocus() // får tastatur fjernet når man trykker på enter

            }
        ){
            Text (modifier=Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally),text="Trykk for å få værmeldingen")



        }

        Text (modifier=Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally),text="Dato er hardkodet til:")
        Text (modifier=Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally),text="17.05.2021")


        /* To do liste mandag den 27.03

        1. Lag en solid url Builder
        2. LAg en Datepicker som bare velger år eller både år og dato

         */



        /*Velge dato: (per idag 27.03) Legg til dette
        DatePicker
        https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#DatePicker(androidx.compose.material3.DatePickerState,androidx.compose.ui.Modifier,androidx.compose.material3.DatePickerFormatter,kotlin.Function1,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.material3.DatePickerColors)
https://m3.material.io/components/date-pickers/overview

        Sted search bar: https://www.youtube.com/watch?v=CfL6Dl2_dAE&ab_channel=PhilippLackner
         */



        if (isClicked== true) {

            var etUiState_MedRespons = UIState.Svar

            if (etUiState_MedRespons != null) {
                VarResponsCard(Response = etUiState_MedRespons)
            }

        }

    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun VarResponsCard (Response: Frost_API_Respons) {

    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(Response.Frost_data.get(0).observations.get(0).value.toString(), modifier = Modifier.padding (16.dp)) // velg først:data.get(0) fordi data bare har 1 eleemnt i data:  kl.1200. OGså .observations.size.toString(), F

    }
}