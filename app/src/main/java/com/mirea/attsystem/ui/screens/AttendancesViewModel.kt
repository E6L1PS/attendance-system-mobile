package com.mirea.attsystem.ui.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirea.attsystem.model.Attendance
import com.mirea.attsystem.repository.AttendanceRepository
import com.mirea.attsystem.util.Resource
import kotlinx.coroutines.launch

class AttendancesViewModel(
    private val attendanceRepository: AttendanceRepository
) : ViewModel() {

    init {
        getAttendances()
    }

    val attendances: MutableLiveData<Resource<List<Attendance>>> = MutableLiveData()

    private fun getAttendances() = viewModelScope.launch {
//        persons.postValue(Resource.Loading())
        val response = attendanceRepository.getAttendances()
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                attendances.postValue(Resource.Success(resultResponse))
            }
        } else {
            attendances.postValue(Resource.Error(response.message()))
        }
    }
}