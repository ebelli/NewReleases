package com.ebelli.newreleases.data.remote

import com.ebelli.newreleases.domain.model.Albums


interface ApiDataStore {

    suspend fun getAlbums(): Albums

}