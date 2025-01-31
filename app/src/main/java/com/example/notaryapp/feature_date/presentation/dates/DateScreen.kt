package com.example.notaryapp.feature_date.presentation.dates

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.notaryapp.feature_date.presentation.dates.components.DateItem
import com.example.notaryapp.feature_date.presentation.util.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun DatesScreen(
    navController: NavController,
    viewModel: DateViewModel = getViewModel()
) {
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route

    // Ejecutar cuando la pantalla está visible y luego se navega hacia atrás
    LaunchedEffect(currentRoute) {
        if (currentRoute == Screen.DatesScreen.route) { // Reemplaza con la ruta de tu pantalla
            viewModel.onEvent(DatesEvent.ReloadDates)
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddEditDateScreen.route)},
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar nota"
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Row (modifier = Modifier.fillMaxWidth()){
                Text("Dates", style = MaterialTheme.typography.displaySmall, color = MaterialTheme.colorScheme.primary)
                Button(
                    onClick = {
                        viewModel.onEvent(DatesEvent.ReloadDates)
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "Reload")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn (modifier = Modifier.fillMaxSize()){
                items(state.dates) { date ->
                    DateItem(
                        date = date,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            },
                        onDeleteClick = {
                            viewModel.onEvent(DatesEvent.DeleteDate(date))
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}