package com.mirea.attsystem.data.repository

import com.mirea.attsystem.data.api.GateApi
import com.mirea.attsystem.domain.model.Gate
import com.mirea.attsystem.domain.repository.GateRepository
import com.mirea.attsystem.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GateRepositoryImpl @Inject constructor(
    private val api: GateApi
) : GateRepository {
    override suspend fun getGates(): Flow<Resource<List<Gate>?>> = flow {
        emit(Resource.loading(null))

        val response = api.getGates()

        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), null))
        }

    }.catch { exception ->
        emit(Resource.error(exception.message ?: "Error occurred", null))
    }.flowOn(Dispatchers.IO)

    override suspend fun addGate(name: String) {
        api.addGate(name)
    }

    override suspend fun deleteGate(name: String) {
        api.deleteGate(name)
    }
}