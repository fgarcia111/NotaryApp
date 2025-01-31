package com.example.notaryapp.feature_date.presentation.dates

import com.example.notaryapp.feature_date.domain.model.Date


data class DateState(
    val dates: List<Date> = emptyList()
)
