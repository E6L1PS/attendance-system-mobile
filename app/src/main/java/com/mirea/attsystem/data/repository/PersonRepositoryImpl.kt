package com.mirea.attsystem.data.repository

import com.mirea.attsystem.data.api.PersonApi
import com.mirea.attsystem.domain.model.Person
import com.mirea.attsystem.domain.repository.PersonRepository
import com.mirea.attsystem.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepositoryImpl @Inject constructor(
    private val api: PersonApi
) : PersonRepository {

    override suspend fun getPersons(): Flow<Resource<List<Person>?>> = flow {
        emit(Resource.loading(null))

        val response = api.getPersons()

        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), null))
        }

    }.catch { exception ->
        emit(Resource.error(exception.message ?: "Error occurred", null))
    }.flowOn(Dispatchers.IO)


    override suspend fun addPerson(person: Person) = api.addPerson(person)

    override suspend fun deletePerson(uid: Long) = api.deletePerson(uid)

    override suspend fun updatePerson(person: Person) = api.updatePerson(person)

}