package com.mirea.attsystem.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mirea.attsystem.domain.model.Gate

@Entity(
    tableName = "gate"
)
data class GateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
) {
    fun toGate() = Gate(
        id = id,
        name = name
    )
}
