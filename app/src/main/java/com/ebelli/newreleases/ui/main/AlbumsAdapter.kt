package com.ebelli.newreleases.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ebelli.newreleases.R
import com.ebelli.newreleases.data.entities.AlbumEntity
import kotlinx.android.synthetic.main.item_list_album.view.*

class AlbumsAdapter: RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    private lateinit var albums: List<AlbumEntity>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val repoView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_album, parent, false) as LinearLayout

        return AlbumsViewHolder(repoView)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(albums[position])
        holder.itemView.setOnClickListener { openAlbum(albums[position].externalUrl, holder.itemView ) }
    }

    override fun getItemCount(): Int = albums.size

    class AlbumsViewHolder(private val albumView: View): RecyclerView.ViewHolder(albumView) {
        fun bind(album: AlbumEntity) = with(albumView) {
            album_name.text = album.name
        }
    }

    fun setData(albums: List<AlbumEntity>) {
        this.albums = albums
    }

    private fun openAlbum(url: String, view: View) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(view.context, shareIntent, null)
    }
}