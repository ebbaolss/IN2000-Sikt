package com.example.in2000_prosjekt.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.in2000_prosjekt.ui.theme.Sikt_lyseblå
import com.example.in2000_prosjekt.ui.theme.Sikt_mellomblå
import com.example.in2000_prosjekt.ui.theme.Sikt_sort
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Sikt_BottomSheet() {

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    val showModalSheet = rememberSaveable {
        mutableStateOf(false)
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = { BottomSheetContent() }
    ) {
        ModalSheetWithAnchor(sheetState, showModalSheet)
    }
}

@Composable
fun BottomSheetContent(){
    Surface(
        color = Sikt_lyseblå,
        modifier = Modifier.height(200.dp),
        // shape = RoundedCornerShape(20.dp) får ikke denne til å se bra ut :((
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Text(
                text = "Finn turer i nærheten",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 20.dp),
                color = Sikt_sort,
                fontWeight = FontWeight.Bold
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
                    .background(Sikt_lyseblå),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
                // Her skal listen over topper i nærheten:
            ) {
                item {
                    Sikt_FinnTurer_card("test",2469, 3,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card("test",2469, 3,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card("test",2469, 3,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card("test",2469, 3,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card("test",2469, 3,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card("test",2469, 3,true, false, true)
                }
                item {
                    Sikt_FinnTurer_card("test",2469, 3,true, false, true)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ModalSheetWithAnchor(sheetState: ModalBottomSheetState, showModalSheet: MutableState<Boolean>) {
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            modifier = Modifier
                .height(165.dp)
                .fillMaxWidth()
                .padding(30.dp),
            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            colors = ButtonDefaults.buttonColors(Sikt_lyseblå),
            onClick = {
                showModalSheet.value = !showModalSheet.value
                scope.launch {
                    sheetState.show()
                }
            } )
        {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    tint = Sikt_mellomblå,
                    contentDescription = "",
                    modifier = Modifier
                )
                Text(text = "Finn turer i nærheten", color = Sikt_sort, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
