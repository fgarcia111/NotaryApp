package com.example.notaryapp.feature_date.domain.use_case

import com.example.notaryapp.feature_date.domain.model.Date
import com.example.notaryapp.feature_date.domain.repository.DateRepository

class DeleteDate (
    private val repository: DateRepository
){
    suspend operator fun invoke(date: Date){
        repository.deleteDate(date)
    }
}