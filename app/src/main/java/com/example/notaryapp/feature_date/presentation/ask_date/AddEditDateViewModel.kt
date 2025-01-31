package com.example.notaryapp.feature_date.presentation.ask_date

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notaryapp.feature_date.domain.model.Date
import com.example.notaryapp.feature_date.domain.model.InvalidDateException
import com.example.notaryapp.feature_date.domain.use_case.DatesUseCases
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AddEditDateViewModel(
    private val useCases: DatesUseCases
):ViewModel() {

    private val _dateDescripcio = mutableStateOf(AddEditDateTextState(
        hint = "Escriu aqui la descripció"
    ))
    val dateDescripcio: State<AddEditDateTextState> = _dateDescripcio

    private val _state = mutableStateOf(AddEditState())
    val state: State<AddEditState> = _state

    private val _save = mutableStateOf<Boolean>(false)
    val save: State<Boolean> = _save

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: AddEditDateEvent){
        when(event){
            is AddEditDateEvent.EnteredDescripcio -> {
                _dateDescripcio.value = dateDescripcio.value.copy(
                    text = event.value
                )
            }

            is AddEditDateEvent.ChangeDescripcioFocus -> {
                _dateDescripcio.value = dateDescripcio.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            dateDescripcio.value.text.isBlank()
                )

            }
            is AddEditDateEvent.EnterDate -> TODO()
            AddEditDateEvent.SaveDate -> {
                viewModelScope.launch {
                    val data = Date(-1,
                        state.value.selectedNotari,
                        state.value.selectedSala,
                        state.value.textDate + " " + state.value.textTime,
                        dateDescripcio.value.text
                    )
                    try {
                        useCases.insertDate(data)
                        _save.value = true
                    } catch (e: InvalidDateException){
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save date"
                            )
                        )
                    }
                    Log.w("InsertVM", data.sala)
                }
            }
            AddEditDateEvent.ShowDialog -> TODO()
            is AddEditDateEvent.ChangeNotariExpanded -> {
                _state.value = state.value.copy(
                    isDropDownNomExpanded = !state.value.isDropDownNomExpanded
                )
            }

            is AddEditDateEvent.ChangeNotariValue -> {
                _state.value = state.value.copy(
                    selectedNotari = event.value, // Guardar el valor seleccionado
                    isDropDownNomExpanded = false // Cerrar el menú al seleccionar
                )
                Log.w("", state.value.selectedNotari)

            }

            is AddEditDateEvent.ChangeSalaExpanded -> {
                _state.value = state.value.copy(
                    isDropDownSalaExpanded = !state.value.isDropDownSalaExpanded
                )
            }
            is AddEditDateEvent.ChangeSalaValue -> {
                _state.value = state.value.copy(
                    selectedSala = event.value, // Guardar el valor seleccionado
                    isDropDownSalaExpanded = false // Cerrar el menú al seleccionar
                )
                Log.w("", state.value.selectedSala)
            }

            is AddEditDateEvent.ChangeDataValue -> {
                _state.value = state.value.copy(
                    textDate = event.value
                )
                Log.w("", state.value.textDate)
            }
            is AddEditDateEvent.ChangeTimeValue -> {
                _state.value = state.value.copy(
                    textTime = event.value
                )
                Log.w("", state.value.textTime)

            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
    }

}