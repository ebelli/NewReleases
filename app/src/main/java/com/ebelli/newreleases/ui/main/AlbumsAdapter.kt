package com.ebelli.newreleases.ui.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ebelli.newreleases.R
import com.ebelli.newreleases.domain.model.Album
import kotlinx.android.synthetic.main.item_list_album.view.*


class AlbumsAdapter: RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    private lateinit var albums: List<Album>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val repoView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_album, parent, false) as LinearLayout

        return AlbumsViewHolder(repoView)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(albums[position])
        holder.itemView.setOnClickListener { openAlbum(albums[position].externalUrls.spotify, holder.itemView ) }
    }

    override fun getItemCount(): Int = albums.size

    class AlbumsViewHolder(private val albumView: View): RecyclerView.ViewHolder(albumView) {
        fun bind(album: Album) = with(albumView) {
            album_name.text = album.name
        }
    }

    fun setData(albums: List<Album>) {
        this.albums = albums
    }

    private fun openAlbum(url: String?, view: View) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(view.context, browserIntent, null)

    }
}