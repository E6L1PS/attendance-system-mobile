package com.mirea.attsystem.data.db.dao

import androidx.room.*
import com.mirea.attsystem.data.db.entities.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {


    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: PersonEntity)

    @Query("SELECT * FROM person WHERE uid = :uid ")
    fun findByUid(uid: Long): Flow<PersonEntity>

    @Update
    suspend fun update(person: PersonEntity)



}