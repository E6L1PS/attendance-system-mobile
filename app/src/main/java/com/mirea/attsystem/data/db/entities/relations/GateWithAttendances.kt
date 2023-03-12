package com.mirea.attsystem.data.db.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.mirea.attsystem.data.db.entities.AttendanceEntity
import com.mirea.attsystem.data.db.entities.GateEntity
import com.mirea.attsystem.data.db.entities.PersonEntity

data class GateWithAttendances(
    @Embedded val gate: GateEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "gate_id"
    )
    val attendances: List<AttendanceEntity>

)