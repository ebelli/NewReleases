package com.ebelli.newreleases.data.remote

import com.ebelli.newreleases.domain.model.Album


interface ApiDataStore {

    suspend fun getAlbums(): List<Album>

}