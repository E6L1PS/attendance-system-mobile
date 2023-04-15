package com.mirea.attsystem.data.api

import com.mirea.attsystem.data.dto.DateDTO
import com.mirea.attsystem.domain.model.Attendance
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AttendanceApi {

    @GET("at/all")
    suspend fun getAttendances(): Response<List<Attendance>>

    @GET("at/{uid}")
    suspend fun getAttendancesByUid(@Path("uid") uid: Long): Response<List<Attendance>>
    
    @GET("at/dates/{uid}")
    suspend fun getAllIntervalsByUid(@Path("uid") uid: Long): Response<List<DateDTO>>
    
    
}