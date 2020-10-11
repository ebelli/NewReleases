package com.ebelli.newreleases.data.repositories

import com.ebelli.newreleases.data.remote.ApiDataStore

class AlbumRepositoryImpl(private val apiDataStore: ApiDataStore): AlbumRepository {

    override suspend fun getAlbums() = apiDataStore.getAlbums()

}