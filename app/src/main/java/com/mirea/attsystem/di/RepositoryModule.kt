package com.mirea.attsystem.di

import com.mirea.attsystem.data.api.AttendanceApi
import com.mirea.attsystem.data.api.GateApi
import com.mirea.attsystem.data.api.PersonApi
import com.mirea.attsystem.data.repository.AttendanceRepositoryImpl
import com.mirea.attsystem.data.repository.GateRepositoryImpl
import com.mirea.attsystem.data.repository.PersonRepositoryImpl
import com.mirea.attsystem.domain.repository.AttendanceRepository
import com.mirea.attsystem.domain.repository.GateRepository
import com.mirea.attsystem.domain.repository.PersonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideAttendanceRepo(api: AttendanceApi): AttendanceRepository = AttendanceRepositoryImpl(api)

    @Provides
    fun providePersonRepo(api: PersonApi): PersonRepository = PersonRepositoryImpl(api)

    @Provides
    fun provideGateRepo(api: GateApi): GateRepository = GateRepositoryImpl(api)

}