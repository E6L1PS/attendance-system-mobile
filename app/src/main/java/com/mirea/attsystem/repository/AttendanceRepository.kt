package com.mirea.attsystem.repository

import com.mirea.attsystem.network.RetrofitInstance

class AttendanceRepository : Repository {
    suspend fun getAttendances() = RetrofitInstance.attendanceApi.getAttendances()
}