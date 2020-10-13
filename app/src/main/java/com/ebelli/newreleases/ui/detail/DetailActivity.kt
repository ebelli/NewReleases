package com.ebelli.newreleases.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ebelli.newreleases.R
import com.ebelli.newreleases.data.entities.AlbumEntity
import com.ebelli.newreleases.ui.main.ALBUM_EXTRA
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

            val album = intent.getParcelableExtra(ALBUM_EXTRA) as AlbumEntity?
            album?.apply {
                detail_album_name.text = name
                detail_album_artist_name.text = artist
                detail_album_artist_tracks.text = totalTracks.toString()
                Glide.with(this@DetailActivity).load(album.image).into(detail_album_image)
            }
    }
}