package com.jpmc.android_challenge.data

import com.jpmc.android_challenge.model.Album
import com.jpmc.android_challenge.utils.Result

interface AlbumsRepository {
    suspend fun getAlbums() : Result<List<Album>>
    suspend fun saveAlbums(list: List<Album>)
}