package com.mirea.attsystem.data.db.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.mirea.attsystem.data.db.entities.AttendanceEntity
import com.mirea.attsystem.data.db.entities.PersonEntity
import com.mirea.attsystem.domain.model.Attendance
import com.mirea.attsystem.domain.model.Person

data class PersonWithAttendances(
    @Embedded val person: PersonEntity,
    @Relation(
        parentColumn = "uid",
        entityColumn = "person_id"
    )
    val attendances: List<AttendanceEntity>
)