package com.mirea.attsystem.ui.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirea.attsystem.data.repository.PersonRepositoryImpl
import com.mirea.attsystem.domain.model.Person
import com.mirea.attsystem.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonsViewModel @Inject constructor(
    private val personRepository: PersonRepositoryImpl
) : ViewModel() {

    init {
        getPersons()
    }

    val persons: MutableLiveData<Resource<List<Person>>> = MutableLiveData()

    val person: MutableLiveData<Person> = MutableLiveData()

    fun addPerson(person: Person) = viewModelScope.launch {
        personRepository.addPerson(person)
        getPersons()
        /* person.value?.let { personRepository.addPerson(it) }*/
    }

    fun getPersons() = viewModelScope.launch {
//        persons.postValue(Resource.Loading())
        val response = personRepository.getPersons()
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                persons.postValue(Resource.Success(resultResponse))
            }
        } else {
            persons.postValue(Resource.Error(response.message()))
        }
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