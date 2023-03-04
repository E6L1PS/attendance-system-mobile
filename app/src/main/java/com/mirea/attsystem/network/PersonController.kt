package com.mirea.attsystem.network


import com.mirea.attsystem.model.Person
import retrofit2.Response
import retrofit2.http.GET

interface PersonController {

    @GET("./person/all")
    suspend fun getPersons(): Response<List<Person>>


}