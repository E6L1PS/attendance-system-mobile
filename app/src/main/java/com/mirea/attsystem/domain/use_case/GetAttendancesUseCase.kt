package com.mirea.attsystem.domain.use_case

import com.mirea.attsystem.data.dto.DateDTO
import com.mirea.attsystem.domain.model.Attendance
import com.mirea.attsystem.domain.repository.AttendanceRepository
import com.mirea.attsystem.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAttendancesUseCase @Inject constructor(
    private val repository: AttendanceRepository
) {
    suspend fun getAttendances(): Flow<Resource<List<Attendance>?>> {
        return repository.getAttendances()
    }

    suspend fun getAttendancesByUid(uid: Long): Flow<Resource<List<Attendance>?>> {
        return repository.getAttendancesByUid(uid)
    }
    suspend fun getDates(uid: Long): Flow<Resource<List<DateDTO>?>> {
        return repository.getAllDatesByUid(uid)
    }
}