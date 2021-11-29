package com.example.lastApp.data.api

import com.example.lastApp.models.doctor.DoctorResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Accept-Version: v1")
    @GET("doctors")
    suspend fun getDoctors(
        @Query("searchQuery") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): DoctorResponse
     /*
    @GET("doctors")
    suspend fun searchDoctors(
        @Query("searchQuery") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): DoctorResponse
    */
}