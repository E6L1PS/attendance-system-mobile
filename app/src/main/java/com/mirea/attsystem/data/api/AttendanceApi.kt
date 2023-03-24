package com.mirea.attsystem.data.api

import com.mirea.attsystem.data.dto.DateDTO
import com.mirea.attsystem.domain.model.Attendance
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.time.LocalDate
import java.time.LocalTime

interface AttendanceApi {

    @GET("at/all")
    suspend fun getAttendances(): Response<List<Attendance>>

    @GET("at/{uid}")
    suspend fun getAttendancesByUid(@Path("uid") uid: Long): Response<List<Attendance>>

    @GET("at/times/{uid}")
    suspend fun getAttendanceTimesByUid(@Path("uid") uid: Long): Response<List<String>>

    @GET("at/perday/{uid}/{date}")
    suspend fun getHoursWorkedPerDayByUid(@Path("uid") uid: Long, @Path("date") date: LocalDate): Response<String>

    @GET("at/dates/{uid}")
    suspend fun getAllDatesByUid(@Path("uid") uid: Long): Response<List<DateDTO>>
}