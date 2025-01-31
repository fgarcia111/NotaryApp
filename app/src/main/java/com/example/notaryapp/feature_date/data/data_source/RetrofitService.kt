package com.example.notaryapp.feature_date.data.data_source

import com.example.notaryapp.feature_date.domain.model.Date
import com.example.notaryapp.feature_date.presentation.ask_date.AddEditDateEvent
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("dates/")
    suspend fun getDates(): List<Date>

    @GET("dates/insert/{name_notari}/{sala}/{date_date}/{descripcio}/")
    suspend fun insertDate(@Path("name_notari") name_notari: String,
                           @Path("sala") sala: String,
                           @Path("date_date") date_date: String,
                           @Path("descripcio") descripcio: String)

    @GET("dates/delete/{date_id}")
    suspend fun deleteDate(@Path("date_id") date_id: Int)
}