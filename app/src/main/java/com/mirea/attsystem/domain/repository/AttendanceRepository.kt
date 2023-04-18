package com.mirea.attsystem.domain.repository

import com.mirea.attsystem.domain.model.DateDTO
import com.mirea.attsystem.domain.model.Attendance
import com.mirea.attsystem.util.Resource
import kotlinx.coroutines.flow.Flow

interface AttendanceRepository {

    suspend fun getAttendances(): Flow<Resource<List<Attendance>?>>

    suspend fun getAttendancesByUid(uid: Long): Flow<Resource<List<Attendance>?>>

    suspend fun getAllDatesByUid(uid: Long): Flow<Resource<List<DateDTO>?>>

    suspend fun deleteAll()

}