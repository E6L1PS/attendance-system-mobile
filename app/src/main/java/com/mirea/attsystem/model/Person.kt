package com.mirea.attsystem.model

data class Person(
    val uid: Long,
    val name: String,
    val lastName: String,
    val jobTitle: String,
    val gender: Char
)
