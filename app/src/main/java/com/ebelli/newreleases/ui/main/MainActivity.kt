package com.ebelli.newreleases.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ebelli.newreleases.R
import com.ebelli.newreleases.ui.utils.Status
import com.google.android.material.snackbar.Snackbar
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
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
//
//        val builder = AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
//
//        builder.setScopes(arrayOf("streaming"))
//        val request = builder.build()
//
//        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
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
                when (result.status) {
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        it.message?.let { message ->
                            val snackbar = Snackbar.make(
                                root,
                                message, Snackbar.LENGTH_LONG
                            )
                            snackbar.show()
                        }
                    }
                    Status.SUCCESS -> {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)

            when (response.type) {
                // Response was successful and contains auth token
                AuthenticationResponse.Type.TOKEN -> {
                    Log.d("Spotify token",response.accessToken)
                }
                // Auth flow returned an error
                AuthenticationResponse.Type.ERROR -> {
                    Log.d("Spotify",response.error)
                }
                AuthenticationResponse.Type.EMPTY -> {
                    Log.d("Spotify","response Empty")
                }

            }// Handle error response
            // Most likely auth flow was cancelled
            // Handle other cases
        }
    }

    companion object {


        // Request code will be used to verify if result comes from the login activity. Can be set to any integer.
        private val REQUEST_CODE = 1337
        private val REDIRECT_URI = "ebelli://newreleases"
        private val CLIENT_ID = "472362e69154418aba9037543bad7ff2"
    }
}