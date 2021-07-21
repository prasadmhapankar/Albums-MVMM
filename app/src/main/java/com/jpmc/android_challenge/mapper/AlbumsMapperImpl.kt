package com.jpmc.android_challenge.mapper

import com.jpmc.android_challenge.db.entity.AlbumEntity
import com.jpmc.android_challenge.model.Album

class AlbumsMapperImpl : AlbumsMapper {
    override fun mapAlbumsModelToEntity(album: Album): AlbumEntity {
        return AlbumEntity(album.id, album.userId, album.title)
    }

    override fun mapAlbumsEntityToModel(albumEntity: AlbumEntity): Album {
        return Album(albumEntity.id, albumEntity.userId, albumEntity.title)
    }
}