package com.mirea.attsystem.data.db.entities.relations

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.mirea.attsystem.data.db.entities.AttendanceEntity
import com.mirea.attsystem.data.db.entities.GateEntity
import com.mirea.attsystem.data.db.entities.PersonEntity
import com.mirea.attsystem.domain.model.Attendance

data class AttendanceWithAll(

    @Embedded val attendance: AttendanceEntity,

    @Relation(
        parentColumn = "gate_id",
        entityColumn = "id"
    )
    val gate: GateEntity,

    @Relation(
        parentColumn = "person_id",
        entityColumn = "uid"
    )
    val person: PersonEntity

) {
    fun toAttendance(a: AttendanceWithAll): Attendance = Attendance(
        id = a.attendance.id,
        status = a.attendance.status,
        date = a.attendance.date,
        person = a.person.toPerson(),
        gate = a.gate.toGate()
    )
}