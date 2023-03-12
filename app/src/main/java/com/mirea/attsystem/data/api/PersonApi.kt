package com.mirea.attsystem.data.api


import com.mirea.attsystem.domain.model.Person
import retrofit2.Response
import retrofit2.http.*

interface PersonApi {

    @GET("person/all")
    suspend fun getPersons(): Response<List<Person>>

    @POST("person/add")
    suspend fun addPerson(@Body person: Person)

    @DELETE("person/delete/{uid}")
    suspend fun deletePerson(@Path("uid") uid: Long)

    @PUT("person/update")
    suspend fun updatePerson(@Body person: Person)

}