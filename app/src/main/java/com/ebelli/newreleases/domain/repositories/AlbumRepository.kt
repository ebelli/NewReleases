package com.ebelli.newreleases.domain.repositories

import com.ebelli.newreleases.domain.model.Album


interface AlbumRepository {

    suspend fun getAlbums() : List<Album>
}