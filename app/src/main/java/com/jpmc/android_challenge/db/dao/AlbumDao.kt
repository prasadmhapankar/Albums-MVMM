package com.jpmc.android_challenge.db.dao

import androidx.room.*
import com.jpmc.android_challenge.db.entity.AlbumEntity

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums")
    fun getAll(): List<AlbumEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(albums: List<AlbumEntity>)

    @Delete
    fun delete(albumEntity: AlbumEntity)
}