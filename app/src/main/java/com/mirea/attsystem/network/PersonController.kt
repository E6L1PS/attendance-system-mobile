package com.mirea.attsystem.network


import com.mirea.attsystem.model.Person
import retrofit2.Response
import retrofit2.http.*

interface PersonController {

    @GET("./person/all")
    suspend fun getPersons(): Response<List<Person>>

    @POST("./person/add")
    suspend fun addPerson(@Body person: Person)

    @DELETE("http://192.168.0.106:8080/person/delete/{uid}")
    suspend fun deletePerson(@Path("uid") uid: Long)


    @DELETE("http://192.168.0.106:8080/person/delete/{uid}")
    suspend fun updatePerson(@Body person: Person)

}