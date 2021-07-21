package com.jpmc.android_challenge.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jpmc.android_challenge.db.dao.AlbumDao
import com.jpmc.android_challenge.db.entity.AlbumEntity
import javax.inject.Singleton

@Singleton
@Database(entities = [AlbumEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}