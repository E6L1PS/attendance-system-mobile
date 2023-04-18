package com.mirea.attsystem.ui.person.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirea.attsystem.domain.model.Person
import com.mirea.attsystem.domain.repository.PersonRepository
import com.mirea.attsystem.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonsViewModel @Inject constructor(
    private val personRepository: PersonRepository
) : ViewModel() {

    private val _persons = MutableStateFlow<Resource<List<Person>?>>(Resource.loading())
    val persons: StateFlow<Resource<List<Person>?>> = _persons.asStateFlow()

    init {
        getPersons()
    }

    fun getPersons() = viewModelScope.launch {
        _persons.emitAll(personRepository.getPersons())
    }

    fun addPerson(person: Person) = viewModelScope.launch {
        personRepository.addPerson(person)
        getPersons()
    }

    fun deletePerson(uid: Long) = viewModelScope.launch {
        personRepository.deletePerson(uid)
        getPersons()
    }

    fun updatePerson(person: Person) = viewModelScope.launch {
        personRepository.updatePerson(person)
        getPersons()
    }


}