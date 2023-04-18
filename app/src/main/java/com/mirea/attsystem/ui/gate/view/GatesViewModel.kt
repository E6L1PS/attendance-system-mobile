package com.mirea.attsystem.ui.gate.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirea.attsystem.domain.model.Gate
import com.mirea.attsystem.domain.repository.GateRepository
import com.mirea.attsystem.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GatesViewModel @Inject constructor(
    private val gateRepository: GateRepository
) : ViewModel() {

    private val _gates = MutableStateFlow<Resource<List<Gate>?>>(Resource.loading())
    val gates: StateFlow<Resource<List<Gate>?>> = _gates.asStateFlow()

    init {
        getGates()
    }

    fun getGates() = viewModelScope.launch {
        _gates.emitAll(gateRepository.getGates())
    }

    fun addGate(name: String) = viewModelScope.launch {
        gateRepository.addGate(name)
        getGates()
    }

    fun deleteGate(name: String) = viewModelScope.launch {
        gateRepository.deleteGate(name)
        getGates()
    }

}