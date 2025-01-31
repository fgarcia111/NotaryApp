package com.example.notaryapp.feature_date.presentation.dates

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.example.notaryapp.feature_date.domain.model.Date
import com.example.notaryapp.feature_date.domain.use_case.DatesUseCases
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DateViewModel(
    private val datesUseCases:  DatesUseCases
): ViewModel(){

    private val _state = mutableStateOf(DateState())
    val state: State<DateState> = _state


    fun onEvent(event: DatesEvent){
        when(event){
            is DatesEvent.DeleteDate -> {
                viewModelScope.launch {
                    datesUseCases.deleteDate(event.date)
                    getDate()
                    Log.w("HOPLA",  "")
                }
            }

            DatesEvent.ReloadDates -> {
                getDate()
            }
        }
    }

    private var getDateJob: Job? = null

    init {
        getDate()
    }

    private fun getDate(){
        getDateJob?.cancel()
        getDateJob = datesUseCases.getDates()
            .onEach { dates ->
                _state.value = state.value.copy(
                    dates = dates.toList()
                )
            }
            .launchIn(viewModelScope)

    }
}