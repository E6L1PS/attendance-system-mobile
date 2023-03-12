package com.mirea.attsystem.data.db.dao

import androidx.room.*
import com.mirea.attsystem.data.db.entities.AttendanceEntity
import com.mirea.attsystem.data.db.entities.relations.AttendanceWithAll


@Dao
interface AttendanceDao {

    @Transaction
    @Query("SELECT * FROM attendance")
    suspend fun findAll(): List<AttendanceWithAll>



    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(at: List<AttendanceEntity>)

}
