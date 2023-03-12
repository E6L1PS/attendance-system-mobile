package com.mirea.attsystem.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mirea.attsystem.domain.model.Person

@Entity(
    tableName = "person"
)
data class PersonEntity(
    @PrimaryKey(autoGenerate = false)
    val uid: Long,
    val name: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "job_title")
    val jobTitle: String,
    val gender: Char
) {
    fun toPerson() = Person(
        uid = uid,
        name = name,
        lastName = lastName,
        jobTitle = jobTitle,
        gender = gender
    )
}
