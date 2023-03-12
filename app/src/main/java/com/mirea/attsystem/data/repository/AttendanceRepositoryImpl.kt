package com.mirea.attsystem.data.repository

import com.mirea.attsystem.data.api.AttendanceApi
import com.mirea.attsystem.domain.repository.AttendanceRepository
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AttendanceRepositoryImpl @Inject constructor(
    //  val db: AttendanceDao
    retrofit: Retrofit
): AttendanceRepository {
    private val api: AttendanceApi = retrofit.create(AttendanceApi::class.java)

    override suspend fun getAttendances() = api.getAttendances()

    override suspend fun getAttendancesByUid(uid: Long) = api.getAttendancesByUid(uid)

    /*   suspend fun insertAttendancesToDb(attendances: List<Attendance>?) =
           attendances?.let {
               db.insertAll(it.map {
                   it.toAttendanceEntity()
               })
           }
   */

}