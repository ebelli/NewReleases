package com.ebelli.newreleases.data.mappers

import com.ebelli.newreleases.data.entities.AlbumEntity
import com.ebelli.newreleases.domain.model.Album

fun Album.toAlbumEntity() = AlbumEntity(
    name = name,
    releaseDate = releaseDate,
    thumbnail = images.filter { it.height == 64 }[0].url,
    image = images.filter { it.height == 300 }[0].url,
    externalUrl = externalUrls.spotify,
    artist = artists[0].name,
    totalTracks = totalTracks
)