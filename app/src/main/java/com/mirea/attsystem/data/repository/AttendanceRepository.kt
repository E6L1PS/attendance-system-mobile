package com.mirea.attsystem.data.repository

import com.mirea.attsystem.data.network.RetrofitInstance

class AttendanceRepository : Repository {
    suspend fun getAttendances() = RetrofitInstance.attendanceApi.getAttendances()
}