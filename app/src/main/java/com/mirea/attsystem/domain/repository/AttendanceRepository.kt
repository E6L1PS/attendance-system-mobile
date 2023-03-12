package com.mirea.attsystem.domain.repository

import com.mirea.attsystem.domain.model.Attendance
import retrofit2.Response

interface AttendanceRepository {

    suspend fun getAttendances(): Response<List<Attendance>>

    suspend fun getAttendancesByUid(uid: Long): Response<List<Attendance>>

}