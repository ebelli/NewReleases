package com.ebelli.newreleases.data.remote

class ApiDataStoreImpl(private val apiService: ApiService): ApiDataStore {

    override suspend fun getAlbums() = apiService.getAlbums()
}