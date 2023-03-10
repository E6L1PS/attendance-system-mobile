package com.mirea.attsystem.data.room.entities

data class PersonEntity(
    val uid: Long,
    val name: String,
    val lastName: String,
    val jobTitle: String,
    val gender: Char
)
