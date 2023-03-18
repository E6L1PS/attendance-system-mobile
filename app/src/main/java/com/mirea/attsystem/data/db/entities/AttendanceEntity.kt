package com.mirea.attsystem.data.db.entities

import androidx.room.*
import com.mirea.attsystem.domain.model.Attendance
import com.mirea.attsystem.domain.model.Gate
import com.mirea.attsystem.domain.model.Person

@Entity(
    tableName = "attendance",
/*    foreignKeys = [
        ForeignKey(
            entity = PersonEntity::class,
            parentColumns = ["uid"],
            childColumns = ["person_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GateEntity::class,
            parentColumns = ["id"],
            childColumns = ["gate_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]*/
)
data class AttendanceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "person_id")
    val personId: Long,

    @ColumnInfo(name = "gate_id")
    val gateId: Int,

    val status: Boolean,
    val date: String
) {
/*
    fun toAttendance() = Attendance(
        id = id,
        person = person,
        gate = gate,
        status = status,
        date = date
    )*/
}
