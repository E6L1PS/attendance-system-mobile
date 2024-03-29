package com.mirea.attsystem.ui.attendance.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirea.attsystem.domain.model.Attendance
import com.mirea.attsystem.domain.model.DateDTO
import com.mirea.attsystem.domain.repository.AttendanceRepository
import com.mirea.attsystem.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendancesViewModel @Inject constructor(
    private val attendanceRepository: AttendanceRepository
) : ViewModel() {

    private val _attendances = MutableStateFlow<Resource<List<Attendance>?>>(Resource.loading())
    val attendances: StateFlow<Resource<List<Attendance>?>> = _attendances.asStateFlow()

    private val _attendancesByUid =
        MutableStateFlow<Resource<List<Attendance>?>>(Resource.loading())
    val attendancesByUid: StateFlow<Resource<List<Attendance>?>> = _attendancesByUid.asStateFlow()

    private val _dates = MutableStateFlow<Resource<List<DateDTO>?>>(Resource.loading())
    val dates: StateFlow<Resource<List<DateDTO>?>> = _dates.asStateFlow()

    init {
        getAttendances()
    }

    fun getAttendances() = viewModelScope.launch {
        _attendances.emitAll(attendanceRepository.getAttendances())
    }

    fun getAttendancesByUid(uid: Long) = viewModelScope.launch {
        _attendancesByUid.emitAll(attendanceRepository.getAttendancesByUid(uid))
    }

    fun getAllDatesByUid(uid: Long) = viewModelScope.launch {
        _dates.emitAll(attendanceRepository.getAllDatesByUid(uid))
    }

    fun deleteAll() = viewModelScope.launch {
        attendanceRepository.deleteAll()
        getAttendances()
    }

}