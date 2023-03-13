package com.example.in2000_prosjekt.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.in2000_prosjekt.R

@Composable
fun RulesScreen(onNavigateToNext: () -> Unit){

    val rules: Array<String> = stringArrayResource(id = R.array.rules)

    Column {

        Text(
            modifier = Modifier,
            text = "Fjellvettreglene",
            textAlign = TextAlign.Center, //den er fortsatt ikke i midten
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(25.dp))

        rules.forEach {
            Text(text = it,
                fontSize = 24.sp
            )
        }
    }

}

//class Rules {
//}