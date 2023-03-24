package com.mirea.attsystem.domain.repository

import com.mirea.attsystem.data.dto.DateDTO
import com.mirea.attsystem.domain.model.Attendance
import retrofit2.Response
import java.time.Duration
import java.time.LocalTime

interface AttendanceRepository {

    suspend fun getAttendances(): Response<List<Attendance>>

    suspend fun getAttendancesByUid(uid: Long): Response<List<Attendance>>

    suspend fun getAttendanceTimesByUid(uid: Long): Response<List<String>>

    suspend fun getAllDatesByUid(uid: Long): Response<List<DateDTO>>

}