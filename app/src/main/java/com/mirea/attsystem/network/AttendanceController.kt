package com.mirea.attsystem.network

import com.mirea.attsystem.model.Attendance
import retrofit2.Response
import retrofit2.http.GET
import java.time.Duration

interface AttendanceController {

    @GET("./at/all")
    suspend fun getAttendances(): Response<List<Attendance>>

    @GET("./at/uid")
    suspend fun getAttendancesByUid(): Response<List<Attendance>>


    @GET("./at/uid")
    suspend fun getAttendanceTimes(): Response<List<Duration>>

}