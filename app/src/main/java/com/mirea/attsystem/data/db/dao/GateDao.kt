package com.mirea.attsystem.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.mirea.attsystem.data.db.entities.GateEntity

@Dao
interface GateDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gate: GateEntity)
}