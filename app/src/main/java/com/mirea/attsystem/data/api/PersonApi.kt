package com.mirea.attsystem.data.api


import com.mirea.attsystem.domain.model.Person
import retrofit2.Response
import retrofit2.http.*

interface PersonApi {

    @GET("api/v1/person")
    suspend fun getPersons(): Response<List<Person>>

    @POST("api/v1/person")
    suspend fun addPerson(@Body person: Person)

    @PUT("api/v1/person")
    suspend fun updatePerson(@Body person: Person)

    @DELETE("api/v1/person/{uid}")
    suspend fun deletePerson(@Path("uid") uid: Long)

}