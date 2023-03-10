package com.mirea.attsystem.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "attendance"
)
data class AttendanceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val person: Person,
    val gate: Gate,
    val status: Boolean,
    val date: String
) {


    fun toAttendance() {
        //TODO
    }

}