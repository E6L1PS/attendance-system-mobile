package com.mirea.attsystem.di

import android.content.Context
import androidx.room.Room
import com.mirea.attsystem.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext application: Context) =
        Room.databaseBuilder(application, AppDatabase::class.java, "attendance_db").build()

    @Singleton
    @Provides
    fun provideAttendanceDao(db: AppDatabase) = db.getAttendanceDao()

    @Singleton
    @Provides
    fun providePersonDao(db: AppDatabase) = db.getPersonDao()

    @Singleton
    @Provides
    fun provideGateDao(db: AppDatabase) = db.getGateDao()

}