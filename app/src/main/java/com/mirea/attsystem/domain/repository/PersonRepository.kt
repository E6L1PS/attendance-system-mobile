package com.mirea.attsystem.domain.repository

import com.mirea.attsystem.domain.model.Person
import com.mirea.attsystem.util.Resource
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    suspend fun getPersons(): Flow<Resource<List<Person>?>>

    suspend fun addPerson(person: Person)

    suspend fun deletePerson(uid: Long)

    suspend fun updatePerson(person: Person)

}
