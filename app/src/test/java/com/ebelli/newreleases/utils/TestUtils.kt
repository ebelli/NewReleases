package com.ebelli.newreleases.utils

import com.ebelli.newreleases.domain.model.Album
import com.ebelli.newreleases.domain.model.Artist
import com.ebelli.newreleases.domain.model.ExternalUrls

fun get2Albums() : List<Album> {
    return listOf<Album>(
        Album(name = "Dire Straits", releaseDate = "1978-10-07", artists = getArtists(), totalTracks = 9,
        images = listOf(), externalUrls = getExternalUrls()),
        Album(name = "Communiqué", releaseDate = "1979-06-15", artists = getArtists(), totalTracks = 9,
        images = listOf(), externalUrls = getExternalUrls())
    )
}

private fun getArtists() = listOf(Artist("Dire Straits"))

private fun getExternalUrls() = ExternalUrls(spotify = "")