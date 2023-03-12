package com.mirea.attsystem.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mirea.attsystem.data.db.dao.AttendanceDao
import com.mirea.attsystem.data.db.dao.GateDao
import com.mirea.attsystem.data.db.dao.PersonDao
import com.mirea.attsystem.data.db.entities.AttendanceEntity
import com.mirea.attsystem.data.db.entities.GateEntity
import com.mirea.attsystem.data.db.entities.PersonEntity

@Database(
    version = 1,
    entities = [
        AttendanceEntity::class,
        GateEntity::class,
        PersonEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPersonDao(): PersonDao

    abstract fun getAttendanceDao(): AttendanceDao

    abstract fun getGateDao(): GateDao
}