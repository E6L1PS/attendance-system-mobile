package com.mirea.attsystem.data.repository

import com.mirea.attsystem.data.network.RetrofitInstance

class AttendanceRepository : Repository {

    suspend fun getAttendances() = RetrofitInstance.attendanceApi.getAttendances()

    suspend fun getAttendancesByUid(uid: Long) = RetrofitInstance.attendanceApi.getAttendancesByUid(uid)

}