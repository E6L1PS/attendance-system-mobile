package com.mirea.attsystem.data.network.api


import com.mirea.attsystem.model.Person
import retrofit2.Response
import retrofit2.http.*

interface PersonApi {

    @GET("person/all")
    suspend fun getPersons(): Response<List<Person>>

    @POST("person/add")
    suspend fun addPerson(@Body person: Person)

    @DELETE("person/delete/{uid}")
    suspend fun deletePerson(@Path("uid") uid: Long)

    @PUT("person/delete/{uid}")
    suspend fun updatePerson(@Body person: Person)

}