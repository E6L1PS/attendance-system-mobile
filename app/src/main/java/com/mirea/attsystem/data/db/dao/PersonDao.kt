package com.mirea.attsystem.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mirea.attsystem.data.db.entities.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {


    @Query("SELECT * FROM person WHERE uid = :uid ")
    fun findByUid(uid: Long): Flow<PersonEntity>

    @Update
    suspend fun update(person: PersonEntity)

    @Insert
    suspend fun create(person: PersonEntity)


}