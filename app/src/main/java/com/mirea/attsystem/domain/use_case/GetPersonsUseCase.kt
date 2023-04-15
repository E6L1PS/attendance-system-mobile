package com.mirea.attsystem.domain.use_case

import com.mirea.attsystem.domain.model.Person
import com.mirea.attsystem.domain.repository.PersonRepository
import com.mirea.attsystem.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPersonsUseCase @Inject constructor(
    private val repository: PersonRepository
) {
    suspend fun getPersons(): Flow<Resource<List<Person>?>> {
        return repository.getPersons()
    }

    suspend fun addPerson(person: Person) {
        return repository.addPerson(person)
    }

    suspend fun deletePerson(uid: Long) {
        return repository.deletePerson(uid)
    }

    suspend fun updatePerson(person: Person) {
        return repository.updatePerson(person)
    }

}
