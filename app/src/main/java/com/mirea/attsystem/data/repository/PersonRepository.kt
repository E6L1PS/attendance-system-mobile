package com.mirea.attsystem.data.repository

import com.mirea.attsystem.model.Person
import com.mirea.attsystem.data.network.RetrofitInstance

class PersonRepository : Repository {
    suspend fun getPersons() = RetrofitInstance.personApi.getPersons()

    suspend fun addPerson(person: Person) = RetrofitInstance.personApi.addPerson(person)

    suspend fun deletePerson(uid: Long) = RetrofitInstance.personApi.deletePerson(uid)

    suspend fun updatePerson(person: Person) = RetrofitInstance.personApi.updatePerson(person)

}