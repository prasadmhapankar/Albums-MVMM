package com.jpmc.android_challenge.data

import android.content.Context
import com.jpmc.android_challenge.data.source.AlbumsLocalDataSource
import com.jpmc.android_challenge.data.source.AlbumsRemoteDataSource
import com.jpmc.android_challenge.model.Album
import com.jpmc.android_challenge.utils.NetworkHelper
import com.jpmc.android_challenge.utils.Result
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val remoteDataSource: AlbumsRemoteDataSource,
    private val localDataSource: AlbumsLocalDataSource) : AlbumsRepository {

    override suspend fun getAlbums(): Result<List<Album>> {
        //If device is online get albums from internet(retrofit call) else get from localDb(Room)
        return if(networkHelper.isOnline()){
            remoteDataSource.getAlbums()
        } else{
            localDataSource.getAlbums()
        }
    }

    override suspend fun saveAlbums(list: List<Album>) {
        localDataSource.saveAlbums(list)
    }

}