package com.mirea.attsystem.data.network.api

import com.mirea.attsystem.model.Attendance
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.time.Duration

interface AttendanceApi {

    @GET("at/all")
    suspend fun getAttendances(): Response<List<Attendance>>

    @GET("at/{uid}")
    suspend fun getAttendancesByUid(@Path("uid") uid: Long): Response<List<Attendance>>

    @GET("at/uid")
    suspend fun getAttendanceTimes(): Response<List<Duration>>

}