package com.ebelli.newreleases.data.repositories

import com.ebelli.newreleases.data.mappers.toAlbumEntity
import com.ebelli.newreleases.data.remote.ApiDataStore
import com.ebelli.newreleases.domain.repositories.AlbumRepository

class AlbumRepositoryImpl(private val apiDataStore: ApiDataStore): AlbumRepository {

    override suspend fun getAlbums() = apiDataStore.getAlbums().albums.items.map { it.toAlbumEntity() }

}