package com.example.notaryapp.feature_date.domain.model

data class Date(
    val id: Int,
    val name_notari: String,
    val sala: String,
    val date_date: String,
    val descripcio: String
)

class InvalidDateException(message: String): Exception(message)