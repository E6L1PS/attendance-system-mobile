package com.mirea.attsystem.data.repository

import com.mirea.attsystem.data.api.AttendanceApi
import com.mirea.attsystem.data.dto.DateDTO
import com.mirea.attsystem.domain.model.Attendance
import com.mirea.attsystem.domain.repository.AttendanceRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AttendanceRepositoryImpl @Inject constructor(
    private val api: AttendanceApi
) : AttendanceRepository {

    override suspend fun getAttendances(): Response<List<Attendance>> {
        return api.getAttendances()
    }

    override suspend fun getAllDatesByUid(uid: Long): Response<List<DateDTO>> =
        api.getAllIntervalsByUid(uid)

    override suspend fun getAttendancesByUid(uid: Long) = api.getAttendancesByUid(uid)


}