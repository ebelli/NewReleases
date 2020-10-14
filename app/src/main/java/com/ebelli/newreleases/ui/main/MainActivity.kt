package com.ebelli.newreleases.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ebelli.newreleases.R
import com.ebelli.newreleases.ui.utils.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModel()
    private val viewAdapter = AlbumsAdapter()

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupObservers()
            initRecyclerView()
        mainViewModel.getAlbums()
    }

    private fun initRecyclerView() {
        val recyclerView = album_list
        val viewManager: RecyclerView.LayoutManager = LinearLayoutManager(this)

        with(recyclerView) {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
    private fun setupObservers() {
        mainViewModel.albums.observe(this, Observer {
            it?.let { result ->
                when (result) {
                    is Resource.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        progressBar.visibility = View.GONE
                        it.message?.let { message ->
                            val snackbar = Snackbar.make(
                                root,
                                message, Snackbar.LENGTH_LONG
                            )
                            snackbar.show()
                        }
                    }
                    is Resource.Success -> {
                        it.data?.let {albums ->
                            viewAdapter.setData(albums)
                            viewAdapter.notifyDataSetChanged()
                            album_list.visibility = View.VISIBLE
                        }
                        progressBar.visibility = View.GONE
                    }
                }
            }
        })
    }
}