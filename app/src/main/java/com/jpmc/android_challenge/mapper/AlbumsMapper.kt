package com.jpmc.android_challenge.mapper

import com.jpmc.android_challenge.db.entity.AlbumEntity
import com.jpmc.android_challenge.model.Album

interface AlbumsMapper {
    fun mapAlbumsModelToEntity(album : Album) : AlbumEntity
    fun mapAlbumsEntityToModel(albumEntity: AlbumEntity) : Album
}