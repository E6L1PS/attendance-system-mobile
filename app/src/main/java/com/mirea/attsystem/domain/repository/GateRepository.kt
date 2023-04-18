package com.mirea.attsystem.domain.repository

import com.mirea.attsystem.domain.model.Gate
import com.mirea.attsystem.util.Resource
import kotlinx.coroutines.flow.Flow

interface GateRepository {

    suspend fun getGates(): Flow<Resource<List<Gate>?>>

    suspend fun addGate(name: String)

    suspend fun deleteGate(name: String)

}