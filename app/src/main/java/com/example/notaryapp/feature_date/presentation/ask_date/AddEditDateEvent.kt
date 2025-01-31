package com.example.notaryapp.feature_date.presentation.ask_date

import androidx.compose.ui.focus.FocusState

sealed class AddEditDateEvent {
    data class EnteredDescripcio(val value: String): AddEditDateEvent()
    data class ChangeDescripcioFocus(val  focusState: FocusState): AddEditDateEvent()
    data class EnterDate(val value: String): AddEditDateEvent()
    data class ChangeNotariValue(val value: String): AddEditDateEvent()
    object ChangeNotariExpanded: AddEditDateEvent()

    data class ChangeSalaValue(val value: String): AddEditDateEvent()
    object ChangeSalaExpanded: AddEditDateEvent()

    data class ChangeTimeValue(val value: String): AddEditDateEvent()
    data class ChangeDataValue(val value: String): AddEditDateEvent()

    object ShowDialog: AddEditDateEvent()
    object SaveDate: AddEditDateEvent()

}