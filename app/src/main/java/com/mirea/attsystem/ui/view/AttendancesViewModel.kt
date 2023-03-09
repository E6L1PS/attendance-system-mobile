package com.mirea.attsystem.ui.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirea.attsystem.data.repository.AttendanceRepository
import com.mirea.attsystem.model.Attendance
import com.mirea.attsystem.util.Resource
import kotlinx.coroutines.launch

class AttendancesViewModel(
    private val attendanceRepository: AttendanceRepository
) : ViewModel() {

    init {
        getAttendances()
    }

    val attendances: MutableLiveData<Resource<List<Attendance>>> = MutableLiveData()

    val attendancesByUid: MutableLiveData<Resource<List<Attendance>>> = MutableLiveData()
    fun getAttendances() = viewModelScope.launch {
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

    fun getAttendancesByUid(uid: Long) = viewModelScope.launch {
//        persons.postValue(Resource.Loading())
        val response = attendanceRepository.getAttendancesByUid(uid)
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                attendancesByUid.postValue(Resource.Success(resultResponse))
            }
        } else {
            attendancesByUid.postValue(Resource.Error(response.message()))
        }
    }


}