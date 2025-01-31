package com.example.notaryapp.feature_date.presentation.ask_date.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notaryapp.feature_date.presentation.ask_date.AddEditDateEvent

@Composable
fun DropDownMenuPro(
    hint: String,
    isExpanded: Boolean,
    listNoms: List<String>,
    changeExpanded: () -> Unit,
    valueNow: String,
    changeValue: (String) -> Unit
) {
    Column {
        // Button or Text acting as a trigger
        Button(
            onClick = { changeExpanded() },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.height(50.dp),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = if (valueNow.isEmpty()) hint else valueNow,
                color = Color.White
            )
        }

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { changeExpanded() }
        ) {
            listNoms.forEach { notari ->
                DropdownMenuItem(
                    text = { Text(text = notari) },
                    onClick = {
                        changeValue(notari) // Guardar selección
                    }
                )
            }
        }
    }
}
