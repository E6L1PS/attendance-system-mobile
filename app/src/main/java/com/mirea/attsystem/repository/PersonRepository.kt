package com.mirea.attsystem.repository

import com.mirea.attsystem.network.RetrofitInstance

class PersonRepository(

) {
    suspend fun getPersons() = RetrofitInstance.api.persons()
}