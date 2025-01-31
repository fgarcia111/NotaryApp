package com.example.notaryapp.feature_date.data.repository

import com.example.notaryapp.feature_date.data.data_source.RetrofitService
import com.example.notaryapp.feature_date.domain.model.Date
import com.example.notaryapp.feature_date.domain.repository.DateRepository
import kotlinx.coroutines.flow.Flow

class DateRepositoryImpl (
    private val retrofitService: RetrofitService
): DateRepository {
    override suspend fun getDates(): List<Date> {
        return retrofitService.getDates()
    }

    override suspend fun insertDate(date: Date) {
        return retrofitService.insertDate(date.name_notari, date.sala, date.date_date, date.descripcio)
    }
    
    override suspend fun deleteDate(date: Date) {
        return retrofitService.deleteDate(date.id)
    }
}