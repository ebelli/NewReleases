package com.ebelli.newreleases.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumEntity(
    val name: String,
    val releaseDate: String, // format : "YYYY-MM-DD"
    val thumbnail: String?,
    val image : String?,
    val externalUrl: String,
    val artist: String, //Simplified version
    val totalTracks: Int
) : Parcelable