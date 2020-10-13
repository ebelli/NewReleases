package com.ebelli.newreleases.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ebelli.newreleases.R
import com.ebelli.newreleases.data.entities.AlbumEntity
import com.ebelli.newreleases.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_list_album.view.*

const val ALBUM_EXTRA = "ALBUM"

class AlbumsAdapter: RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {


    private lateinit var albums: List<AlbumEntity>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val repoView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_album, parent, false) as LinearLayout

        return AlbumsViewHolder(repoView)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    override fun getItemCount(): Int = albums.size

    fun setData(albums: List<AlbumEntity>) {
        this.albums = albums
    }

    class AlbumsViewHolder(private val albumView: View): RecyclerView.ViewHolder(albumView) {

        fun bind(album: AlbumEntity) = with(albumView) {
            detail_album_name.text = album.name
            album_date.text = album.releaseDate

            Glide.with(this).load(album.thumbnail).into(album_image)
            album_share.setOnClickListener { openAlbum(album.externalUrl, itemView) }
            itemView.setOnClickListener{ openDetails(album, itemView) }
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

        private fun openDetails(album: AlbumEntity, view: View) {
            val intent = Intent(view.context, DetailActivity::class.java)
                .apply {
                    putExtra(ALBUM_EXTRA, album)
                }
            startActivity(view.context, intent, null)
        }
    }
}