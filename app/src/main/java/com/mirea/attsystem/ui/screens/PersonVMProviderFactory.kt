package com.mirea.attsystem.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mirea.attsystem.repository.PersonRepository

class PersonVMProviderFactory(
    val personRepository: PersonRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonsViewModel(personRepository) as T
    }
}