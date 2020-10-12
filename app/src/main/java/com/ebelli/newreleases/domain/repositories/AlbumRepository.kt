package com.ebelli.newreleases.domain.repositories

import com.ebelli.newreleases.data.entities.AlbumEntity


interface AlbumRepository {

    suspend fun getAlbums() : List<AlbumEntity>
}