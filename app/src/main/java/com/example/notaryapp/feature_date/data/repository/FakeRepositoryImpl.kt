package com.example.notaryapp.feature_date.data.repository

import android.util.Log
import com.example.notaryapp.feature_date.data.data_source.RetrofitService
import com.example.notaryapp.feature_date.domain.model.Date
import com.example.notaryapp.feature_date.domain.repository.DateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepositoryImpl(
    private val retrofitService: RetrofitService
) : DateRepository {
    private val fakeDates = mutableListOf<Date>()

    init {
        // Initialize with some fake dates
        fakeDates.add(
            Date(
                id = 1,
                name_notari = "Notary 1",
                sala = "Room 101",
                date_date = "2025-01-29 01:05:00",
                descripcio = "Meeting with client"
            )
        )
        fakeDates.add(
            Date(
                id = 2,
                name_notari = "Notary 2",
                sala = "Room 202",
                date_date = "2025-01-29 01:05:00",
                descripcio = "Notary appointment"
            )
        )
        fakeDates.add(
            Date(
                id = 3,
                name_notari = "Notary 3",
                sala = "Room 303",
                date_date = "2025-01-29 01:05:00",
                descripcio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam vel turpis ac libero cursus tempor. Aenean non eros nec nisl tincidunt feugiat in in justo. Suspendisse ac justo et justo dapibus aliquet. Fusce ultricies sapien eget elit tristique, quis lobortis lacus placerat. Proin vitae odio vel erat pharetra tincidunt nec eget eros. Quisque auctor, urna ac sollicitudin vulputate, risus augue faucibus magna, a congue risus felis id augue. Phasellus blandit ligula at velit dapibus, quis convallis mauris pretium."
            )
        )
        fakeDates.add(
            Date(
                id = 4,
                name_notari = "Notary 4",
                sala = "Room 404",
                date_date = "2025-01-29 01:05:00",
                descripcio = "Document signing"
            )
        )
        fakeDates.add(
            Date(
                id = 5,
                name_notari = "Notary 5",
                sala = "Room 505",
                date_date = "2025-01-29 01:05:00",
                descripcio = "Document signing"
            )
        )
        fakeDates.add(
            Date(
                id = 6,
                name_notari = "Notary 6",
                sala = "Room 606",
                date_date = "2025-01-29 01:05:00",
                descripcio = "Document signing"
            )
        )
        fakeDates.add(
            Date(
                id = 7,
                name_notari = "Notary 7",
                sala = "Room 707",
                date_date = "2025-01-29 01:05:00",
                descripcio = "Document signing"
            )
        )
        fakeDates.add(
            Date(
                id = 8,
                name_notari = "Notary 8",
                sala = "Room 808",
                date_date = "2025-01-29 01:05:00",
                descripcio = "Document signing"
            )
        )
    }

    override suspend fun getDates(): List<Date> {
        return fakeDates;
    }

    override suspend fun insertDate(date: Date) {
        fakeDates.add(date)
    }

    override suspend fun deleteDate(date: Date) {
        fakeDates.remove(date)
    }
}