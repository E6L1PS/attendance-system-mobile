package com.mirea.attsystem.data.api

import com.mirea.attsystem.domain.model.DateDTO
import com.mirea.attsystem.domain.model.Attendance
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AttendanceApi {

    @GET("api/v1/attendance")
    suspend fun getAttendances(): Response<List<Attendance>>

    @GET("api/v1/attendance/{uid}")
    suspend fun getAttendancesByUid(@Path("uid") uid: Long): Response<List<Attendance>>
    
    @GET("api/v1/attendance/dates/{uid}")
    suspend fun getAllIntervalsByUid(@Path("uid") uid: Long): Response<List<DateDTO>>
    
    
}