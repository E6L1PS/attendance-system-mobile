package com.mirea.attsystem.domain.model

import com.mirea.attsystem.data.db.entities.PersonEntity

data class Person(
    val uid: Long,
    val name: String,
    val lastName: String,
    val jobTitle: String,
    val gender: Char
) {
    fun toPersonEntity() = PersonEntity(
        uid = uid,
        name = name,
        lastName = lastName,
        jobTitle = jobTitle,
        gender = gender
    )
}
