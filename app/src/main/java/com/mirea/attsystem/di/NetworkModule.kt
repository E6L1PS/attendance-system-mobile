package com.mirea.attsystem.di

import com.mirea.attsystem.data.api.AttendanceApi
import com.mirea.attsystem.data.api.GateApi
import com.mirea.attsystem.data.api.PersonApi
import com.mirea.attsystem.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, converter: GsonConverterFactory): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(converter)
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideAttendanceApi(retrofit: Retrofit) = retrofit.create(AttendanceApi::class.java)

    @Provides
    @Singleton
    fun providePersonApi(retrofit: Retrofit) = retrofit.create(PersonApi::class.java)

    @Provides
    @Singleton
    fun provideGateApi(retrofit: Retrofit) = retrofit.create(GateApi::class.java)



}