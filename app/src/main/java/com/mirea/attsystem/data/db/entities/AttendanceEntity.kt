package com.mirea.attsystem.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mirea.attsystem.domain.model.Attendance

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
