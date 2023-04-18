package com.mirea.attsystem.data.api

import com.mirea.attsystem.domain.model.Gate
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GateApi {

    @GET("api/v1/gate")
    suspend fun getGates(): Response<List<Gate>>

    @POST("api/v1/gate/{name}")
    suspend fun addGate(@Path("name") name: String)

    @DELETE("api/v1/gate/{name}")
    suspend fun deleteGate(@Path("name") name: String)

}