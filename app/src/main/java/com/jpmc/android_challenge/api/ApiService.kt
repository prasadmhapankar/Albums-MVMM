package com.jpmc.android_challenge.api

import com.jpmc.android_challenge.model.Album
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(ApiConstants.ALBUMS)
    suspend fun getAlbums() : Response<List<Album>>

}