package com.example.notaryapp.feature_date.presentation.dates

import com.example.notaryapp.feature_date.domain.model.Date

sealed class DatesEvent {
    data class DeleteDate(val date: Date): DatesEvent()
    object ReloadDates: DatesEvent()
}