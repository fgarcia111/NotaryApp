package com.example.notaryapp.di

import com.example.notaryapp.feature_date.data.data_source.RetrofitService
import com.example.notaryapp.feature_date.data.repository.DateRepositoryImpl
import com.example.notaryapp.feature_date.data.repository.FakeRepositoryImpl
import com.example.notaryapp.feature_date.domain.repository.DateRepository
import com.example.notaryapp.feature_date.domain.use_case.DatesUseCases
import com.example.notaryapp.feature_date.domain.use_case.DeleteDate
import com.example.notaryapp.feature_date.domain.use_case.GetDates
import com.example.notaryapp.feature_date.domain.use_case.InsertDate
import com.example.notaryapp.feature_date.presentation.ask_date.AddEditDateViewModel
import com.example.notaryapp.feature_date.presentation.dates.DateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://192.168.232.176:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
    single<DateRepository> {
        DateRepositoryImpl(get())
    }

    single {
        DatesUseCases(
            getDates = GetDates(get()),
            deleteDate = DeleteDate(get()),
            insertDate = InsertDate(get())
        )
    }

    viewModel { DateViewModel(get()) }
    viewModel { AddEditDateViewModel(get()) }
}
