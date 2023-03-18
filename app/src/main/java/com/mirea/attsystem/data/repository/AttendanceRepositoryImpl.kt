package com.mirea.attsystem.data.repository

import com.mirea.attsystem.data.api.AttendanceApi
import com.mirea.attsystem.data.db.dao.AttendanceDao
import com.mirea.attsystem.data.db.dao.GateDao
import com.mirea.attsystem.data.db.dao.PersonDao
import com.mirea.attsystem.domain.model.Attendance
import com.mirea.attsystem.domain.repository.AttendanceRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AttendanceRepositoryImpl @Inject constructor(
    private val attendanceDao: AttendanceDao,
    private val api: AttendanceApi,
    private val personDao: PersonDao,
    private val gateDao: GateDao
) : AttendanceRepository {

    override suspend fun getAttendances(): Response<List<Attendance>> {
        var response = api.getAttendances()
        var attendances = response.body()
        insertAttendancesToDb(attendances)
        return response
    }

    override suspend fun getAttendancesByUid(uid: Long) = api.getAttendancesByUid(uid)

    suspend fun insertAttendancesToDb(attendances: List<Attendance>?) =
        attendances?.let {
            attendanceDao.insertAll(it.map { attendance ->
                with(attendance) {
                    gateDao.insert(gate.toGateEntity())
                    personDao.insert(person.toPersonEntity())
                    toAttendanceEntity()
                }

            })
        }

}