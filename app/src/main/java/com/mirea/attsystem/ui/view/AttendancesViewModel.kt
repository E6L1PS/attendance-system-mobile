package com.mirea.attsystem.ui.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirea.attsystem.data.dto.DateDTO
import com.mirea.attsystem.data.repository.AttendanceRepositoryImpl
import com.mirea.attsystem.domain.model.Attendance
import com.mirea.attsystem.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class AttendancesViewModel @Inject constructor(
    private val attendanceRepository: AttendanceRepositoryImpl
) : ViewModel() {

    val attendances: MutableLiveData<Resource<List<Attendance>>> = MutableLiveData()

    init {
        getAttendances()
    }

    val attendancesByUid: MutableLiveData<Resource<List<Attendance>>> = MutableLiveData()

    val timesByUid: MutableLiveData<Resource<List<String>>> = MutableLiveData()

    val dates: MutableLiveData<Resource<List<DateDTO>>> = MutableLiveData()
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

    fun getTimesByUid(uid: Long) = viewModelScope.launch {
//        persons.postValue(Resource.Loading())
        val response = attendanceRepository.getAttendanceTimesByUid(uid)
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                timesByUid.postValue(Resource.Success(resultResponse))
            }
        } else {
            timesByUid.postValue(Resource.Error(response.message()))
        }
    }


    fun getAllDatesByUid(uid: Long) = viewModelScope.launch {
//        persons.postValue(Resource.Loading())
        val response = attendanceRepository.getAllDatesByUid(uid)
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                dates.postValue(Resource.Success(resultResponse))
            }
        } else {
            dates.postValue(Resource.Error(response.message()))
        }
    }

}