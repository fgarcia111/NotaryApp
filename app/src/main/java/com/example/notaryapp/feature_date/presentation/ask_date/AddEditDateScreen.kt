package com.example.notaryapp.feature_date.presentation.ask_date

import DateTimePicker
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.notaryapp.feature_date.presentation.ask_date.compose.DropDownMenuPro
import com.example.notaryapp.feature_date.presentation.ask_date.compose.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEditDateScreen(
    navController: NavController,
    viewModel: AddEditDateViewModel = getViewModel(),
){
    val descState = viewModel.dateDescripcio.value
    val snackbarHostState = remember { SnackbarHostState() }
    val state = viewModel.state.value
    val save = viewModel.save.value
    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(0x45745423)
        )
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditDateViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

            }


        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditDateEvent.SaveDate)
                if (save){
                    navController.navigateUp()
                }
            },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save note")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(paddingValues)
                .padding(16.dp)
        ){
            Text(
                "Nova Cita",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary,
                )
            Column ( modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Notari", style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 10.dp)
                    )
                HorizontalDivider(
                    modifier = Modifier.padding(top = 3.dp, bottom = 8.dp, end = 30.dp),
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    DropDownMenuPro(
                        hint = "Selecciona un Notari",
                        isExpanded = state.isDropDownNomExpanded,
                        changeExpanded = { viewModel.onEvent(AddEditDateEvent.ChangeNotariExpanded) },
                        listNoms = state.llistaNoms,
                        valueNow = state.selectedNotari,
                        changeValue = { notari ->
                            viewModel.onEvent(AddEditDateEvent.ChangeNotariValue(notari))
                        })
                    Text(
                        if (state.selectedNotari.isEmpty()) "" else ": " + state.selectedNotari,
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Sala", style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 10.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.padding(top = 3.dp, bottom = 8.dp, end = 30.dp),
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    DropDownMenuPro(
                        hint = "Selecciona una Sala",
                        isExpanded = state.isDropDownSalaExpanded,
                        changeExpanded = { viewModel.onEvent(AddEditDateEvent.ChangeSalaExpanded) },
                        listNoms = state.llistaSalas,
                        valueNow = state.selectedSala,
                        changeValue = { sala ->
                            viewModel.onEvent(AddEditDateEvent.ChangeSalaValue(sala))
                        })
                    Text(
                        if (state.selectedSala.isEmpty()) "" else ": " + state.selectedSala,
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 5.dp)
                    )

                }
                Spacer(modifier = Modifier.height(16.dp))
            }


            Text(
                "Dia i hora", style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 10.dp)
            )
            HorizontalDivider(
                modifier = Modifier.padding(top = 3.dp, bottom = 8.dp, end = 30.dp),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primary
            )
            Row(){
                DateTimePicker { date, time ->
                    viewModel.onEvent(AddEditDateEvent.ChangeDataValue(date.formatDate().toString()))
                    viewModel.onEvent(AddEditDateEvent.ChangeTimeValue(time.formatTime().toString()))
                }
                Column (modifier = Modifier.padding(start = 8.dp)){
                    Text("Dia: ${state.textDate}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,)
                    Text("Hora: ${state.textTime}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,)
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text("Descripció", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(start = 10.dp))

            HorizontalDivider(
                modifier = Modifier.padding(top = 3.dp, bottom = 8.dp, end = 30.dp),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primary
            )
            TransparentHintTextField(
                text = descState.text,
                hint = descState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditDateEvent.EnteredDescripcio(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditDateEvent.ChangeDescripcioFocus(it))
                },
                isHintVisible = descState.isHintVisible,
                singleLine = false,
                textStyle = MaterialTheme.typography.bodyLarge
            )


        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("DefaultLocale")
private fun LocalTime.formatTime(): String {
    return String.format("%02d:%02d:00", this.hour, this.minute)
}

// Keep the date formatting the same
@SuppressLint("SimpleDateFormat")
private fun Date.formatDate(): String {
    return SimpleDateFormat("yyyy-MM-dd").format(this)
}


