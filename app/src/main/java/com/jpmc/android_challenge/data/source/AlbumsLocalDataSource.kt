package com.jpmc.android_challenge.data.source

import com.jpmc.android_challenge.db.AppDatabase
import com.jpmc.android_challenge.mapper.AlbumsMapper
import com.jpmc.android_challenge.model.Album
import com.jpmc.android_challenge.utils.Result
import javax.inject.Inject

class AlbumsLocalDataSource @Inject constructor(private val appDatabase: AppDatabase,
                                                private val mapper: AlbumsMapper) {

    fun getAlbums() : Result<List<Album>>{
        val data = appDatabase.albumDao().getAll().map {
            mapper.mapAlbumsEntityToModel(it)
        }
        return if(!data.isNullOrEmpty())
                    Result.success(data)
               else
                   Result.error("No data", null)
    }

    suspend fun saveAlbums(albumsList: List<Album>){
        val data = albumsList.map {
            mapper.mapAlbumsModelToEntity(it)
        }
        appDatabase.albumDao().insertAll(data)
    }

}