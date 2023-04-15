package com.mirea.attsystem.domain.model

data class Attendance(
    val id: Long,
    val person: Person,
    val gate: Gate,
    val status: Boolean,
    val date: String
)