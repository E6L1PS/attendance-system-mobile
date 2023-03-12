package com.mirea.attsystem.data.repository

import com.mirea.attsystem.data.api.PersonApi
import com.mirea.attsystem.domain.model.Person
import com.mirea.attsystem.domain.repository.PersonRepository
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepositoryImpl @Inject constructor(
    retrofit: Retrofit
): PersonRepository {

    private val api: PersonApi = retrofit.create(PersonApi::class.java)

    override suspend fun getPersons() = api.getPersons()

    override suspend fun addPerson(person: Person) = api.addPerson(person)

    override suspend fun deletePerson(uid: Long) = api.deletePerson(uid)

    override suspend fun updatePerson(person: Person) = api.updatePerson(person)

}