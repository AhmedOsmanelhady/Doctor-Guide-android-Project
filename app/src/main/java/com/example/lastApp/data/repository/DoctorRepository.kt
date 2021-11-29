package com.example.lastApp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.lastApp.data.api.ApiService
import com.example.lastApp.data.DoctorPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DoctorRepository @Inject constructor(
    private val apiService: ApiService,
) {


 //   suspend fun getDoctors(query : String , page: Int, perPage: Int) = apiService.getDoctors(query , page, perPage)


    fun getDoctors(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ), pagingSourceFactory = { DoctorPagingSource(apiService, query) }
        ).liveData
}