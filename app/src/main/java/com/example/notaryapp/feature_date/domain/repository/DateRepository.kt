package com.example.notaryapp.feature_date.domain.repository

import com.example.notaryapp.feature_date.domain.model.Date
import kotlinx.coroutines.flow.Flow

interface DateRepository {

    suspend fun getDates(): List<Date>

    suspend fun insertDate(date: Date)

    suspend fun deleteDate(date: Date)
}