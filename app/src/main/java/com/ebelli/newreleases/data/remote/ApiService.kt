package com.ebelli.newreleases.data.remote

import com.ebelli.newreleases.domain.model.Album
import retrofit2.http.GET

interface ApiService {

    @GET("new-releases")
    suspend fun getAlbums(): List<Album>
}