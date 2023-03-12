package com.mirea.attsystem.domain.model

import com.mirea.attsystem.data.db.entities.GateEntity

data class Gate(
    val id: Int,
    val name: String
) {
    fun toGateEntity(): GateEntity = GateEntity(
        id = id,
        name = name
    )
}