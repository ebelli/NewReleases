package com.ebelli.newreleases.data.mappers

import com.ebelli.newreleases.data.entities.AlbumEntity
import com.ebelli.newreleases.domain.model.Album

fun Album.toAlbumEntity() = AlbumEntity(
    name = name,
    releaseDate = releaseDate,
    images = images,
    externalUrl = externalUrls.spotify,
    artist = artists[0].name,
    totalTracks = totalTracks
)