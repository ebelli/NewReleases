package com.ebelli.newreleases.data.entities

import com.ebelli.newreleases.domain.model.Image


//album name, release date, album image, external_url, artist, label and track count
data class AlbumEntity(
    val name: String,
    val releaseDate: String, // format : "YYYY-MM-DD"
    val images : List<Image>,
    val externalUrl: String,
    val artist: String, //Simplified version
    val totalTracks: Int
)