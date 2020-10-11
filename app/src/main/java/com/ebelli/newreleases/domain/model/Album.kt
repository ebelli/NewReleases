package com.ebelli.newreleases.domain.model

import com.google.gson.annotations.SerializedName


//album name, release date, album image, external_urls, artist, label and track count
data class Album(
    val name: String,
    @SerializedName("release_date")
    val releaseDate: String, // format : "YYYY-MM-DD"
    val images : List<Image>,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val artists: List<Artist>,
    @SerializedName("total_tracks")
    val totalTracks: Int
)