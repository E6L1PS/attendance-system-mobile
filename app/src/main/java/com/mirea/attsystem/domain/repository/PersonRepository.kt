package com.mirea.attsystem.domain.repository

import com.mirea.attsystem.domain.model.Person
import retrofit2.Response

interface PersonRepository {

    suspend fun getPersons(): Response<List<Person>>

    suspend fun addPerson(person: Person)

    suspend fun deletePerson(uid: Long)

    suspend fun updatePerson(person: Person)

}
