package com.example.notaryapp.feature_date.domain.use_case

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.notaryapp.feature_date.domain.model.Date
import com.example.notaryapp.feature_date.domain.model.InvalidDateException
import com.example.notaryapp.feature_date.domain.repository.DateRepository
import kotlinx.coroutines.flow.first
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.jvm.Throws

class InsertDate (
    private val repository: DateRepository
){
    @Throws(InvalidDateException::class)
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(date: Date) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val listaFechas = repository.getDates()// Obtener la lista de fechas actuales
        val dateTime = LocalDateTime.parse(date.date_date, formatter)

        // Filtrar solo fechas del mismo notario
        val listaFechasConvertidas = listaFechas.filter { it.name_notari == date.name_notari }.mapNotNull {
            runCatching { LocalDateTime.parse(it.date_date, formatter) }.getOrNull()
        }

        if (verificarData(dateTime, listaFechasConvertidas)) {
            Log.w("InsertUC", "Fecha válida, insertando: $dateTime")
            repository.insertDate(date)
        } else {
            Log.w("InsertUC", "Fecha no válida: $dateTime")
            throw InvalidDateException("Error a la creació de la cita")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun verificarData(fecha: LocalDateTime, listaFechas: List<LocalDateTime>): Boolean {
    val ahora = LocalDateTime.now()

    // Verificar que la fecha sea mayor que la actual
    if (fecha.isBefore(ahora)) {
        return false
    }

    // Verificar que ninguna fecha en la lista esté en un rango de una hora
    for (f in listaFechas) {
        val diferencia = ChronoUnit.HOURS.between(f, fecha)
        if (kotlin.math.abs(diferencia) < 1) {
            return false
        }
    }

    return true
}