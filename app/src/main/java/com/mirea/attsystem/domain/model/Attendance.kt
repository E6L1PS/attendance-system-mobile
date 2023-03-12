package com.mirea.attsystem.domain.model

import com.mirea.attsystem.data.db.entities.AttendanceEntity
import com.mirea.attsystem.data.db.entities.relations.AttendanceWithAll

data class Attendance(
    val id: Long,
    val person: Person,
    val gate: Gate,
    val status: Boolean,
    val date: String
) {

    fun toAttendanceEntity(): AttendanceEntity = AttendanceEntity(
        id = id,
        status = status,
        date = date,
        personId = person.uid,
        gateId = gate.id
    )
    fun toAttendanceWithAll(a: Attendance): AttendanceWithAll = AttendanceWithAll(
        attendance = a.toAttendanceEntity(),
        person = a.person.toPersonEntity(),
        gate = a.gate.toGateEntity()
    )
}