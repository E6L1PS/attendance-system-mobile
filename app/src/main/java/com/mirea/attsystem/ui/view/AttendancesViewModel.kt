package com.mirea.attsystem.ui.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirea.attsystem.data.repository.AttendanceRepositoryImpl
import com.mirea.attsystem.domain.model.Attendance
import com.mirea.attsystem.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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