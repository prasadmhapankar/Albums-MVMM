package com.jpmc.android_challenge.data.source

import com.jpmc.android_challenge.api.ApiService
import com.jpmc.android_challenge.model.Album
import com.jpmc.android_challenge.utils.Result
import javax.inject.Inject

class AlbumsRemoteDataSource @Inject constructor(private val apiService: ApiService) : BaseDataSource() {

    suspend fun getAlbums() : Result<List<Album>>{
        return getResult { apiService.getAlbums() }
    }

}