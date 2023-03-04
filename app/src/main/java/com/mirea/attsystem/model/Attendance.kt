package com.mirea.attsystem.model

data class Attendance(
    val id: Long,
    val person: Person,
    val gate: Gate,
    val status: Boolean,
    val date: String
)