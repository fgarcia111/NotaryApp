package com.example.notaryapp.feature_date.domain.use_case

import com.example.notaryapp.feature_date.domain.model.Date
import com.example.notaryapp.feature_date.domain.repository.DateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDates (
    private val repository: DateRepository
) {
    operator fun invoke(): Flow<List<Date>> {
        return flow {
            emit(repository.getDates())
        }
    }
}