package com.ebelli.newreleases.utils

import com.ebelli.newreleases.data.entities.AlbumEntity
import com.ebelli.newreleases.domain.model.*

fun get2Albums() : Albums =
    Albums(Paging(
        items = getAlbumList(),
        href = "https://api.spotify.com/v1/albums/6eRhTVavbbpeYhcSOjpJwr",
        limit = 20,
        next = "https://api.spotify.com/v1/browse/new-releases?offset=20&limit=20",
        offset = 0,
        previous = null,
        total = 100
        )
    )

fun getAlbumEntityList() : List<AlbumEntity> {
    return listOf<AlbumEntity>(
        AlbumEntity(name = "Dire Straits", releaseDate = "1978-10-07", artist = "Dire Straits", totalTracks = 9,
            images = listOf(), externalUrl = ""),
        AlbumEntity(name = "Communiqué", releaseDate = "1979-06-15", artist = "Dire Straits", totalTracks = 9,
            images = listOf(), externalUrl = "")
    )
}

private fun getAlbumList() : List<Album> {
    return listOf<Album>(
        Album(name = "Dire Straits", releaseDate = "1978-10-07", artists = getArtists(), totalTracks = 9,
        images = listOf(), externalUrls = getExternalUrls()),
        Album(name = "Communiqué", releaseDate = "1979-06-15", artists = getArtists(), totalTracks = 9,
        images = listOf(), externalUrls = getExternalUrls())
    )
}

private fun getArtists() = listOf(Artist("Dire Straits"))

private fun getExternalUrls() = ExternalUrls(spotify = "")