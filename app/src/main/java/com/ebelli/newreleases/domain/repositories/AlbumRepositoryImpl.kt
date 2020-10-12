package com.ebelli.newreleases.domain.repositories

import com.ebelli.newreleases.data.remote.ApiDataStore

class AlbumRepositoryImpl(private val apiDataStore: ApiDataStore): AlbumRepository {

    override suspend fun getAlbums() = apiDataStore.getAlbums()

}