package com.mirea.attsystem.data.repository

import com.mirea.attsystem.data.api.AttendanceApi
import com.mirea.attsystem.domain.model.DateDTO
import com.mirea.attsystem.domain.model.Attendance
import com.mirea.attsystem.domain.repository.AttendanceRepository
import com.mirea.attsystem.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AttendanceRepositoryImpl @Inject constructor(
    private val api: AttendanceApi
) : AttendanceRepository {

    override suspend fun getAttendances(): Flow<Resource<List<Attendance>?>> = flow {
        emit(Resource.loading(null))

        val response = api.getAttendances()

        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), null))
        }

    }.catch { exception ->
        emit(Resource.error(exception.message ?: "Error occurred", null))
    }.flowOn(Dispatchers.IO)

    override suspend fun getAttendancesByUid(uid: Long): Flow<Resource<List<Attendance>?>> = flow {
        emit(Resource.loading(null))

        val response = api.getAttendancesByUid(uid)

        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), null))
        }

    }.catch { exception ->
        emit(Resource.error(exception.message ?: "Error occurred", null))
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllDatesByUid(uid: Long): Flow<Resource<List<DateDTO>?>> = flow {
        emit(Resource.loading(null))

        val response = api.getAllIntervalsByUid(uid)

        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), null))
        }

    }.catch { exception ->
        emit(Resource.error(exception.message ?: "Error occurred", null))
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteAll() {
        api.deleteAll()
    }
}