package com.ebelli.newreleases.data.repositories

import com.ebelli.newreleases.domain.model.Album


interface AlbumRepository {

    suspend fun getAlbums() : List<Album>
}