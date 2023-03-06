package com.mirea.attsystem.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mirea.attsystem.data.repository.AttendanceRepository
import com.mirea.attsystem.data.repository.PersonRepository
import com.mirea.attsystem.data.repository.Repository

class VMProviderFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (repository) {
            is AttendanceRepository ->  AttendancesViewModel(repository)
            is PersonRepository ->  PersonsViewModel(repository)
            else -> {
                object : ViewModel() {

                }
            }
        } as T
    }
}