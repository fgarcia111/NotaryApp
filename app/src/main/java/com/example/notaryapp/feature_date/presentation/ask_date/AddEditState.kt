package com.example.notaryapp.feature_date.presentation.ask_date

data class AddEditState (
    val llistaNoms: List<String> = listOf("Ferran.G", "Oriol.C"),
    val selectedNotari: String = "",
    val isDropDownNomExpanded: Boolean = false,


    val llistaSalas: List<String> = listOf("Sala1", "Sala2", "Sala3", "Sala4"),
    val selectedSala: String = "",
    val isDropDownSalaExpanded: Boolean = false,

    val textDate: String = "",
    val textTime: String = "",
)