package com.mirea.attsystem.repository

import com.mirea.attsystem.network.RetrofitInstance

class PersonRepository : Repository {
    suspend fun getPersons() = RetrofitInstance.personApi.getPersons()
}