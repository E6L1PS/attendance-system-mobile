package com.mirea.attsystem.ui.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirea.attsystem.model.Person
import com.mirea.attsystem.repository.PersonRepository
import com.mirea.attsystem.util.Resource
import kotlinx.coroutines.launch

class PersonsViewModel(
    private val personRepository: PersonRepository
) : ViewModel() {

    init {
        getPersons()
    }

    val persons: MutableLiveData<Resource<List<Person>>> = MutableLiveData()

    private fun getPersons() = viewModelScope.launch {
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



}