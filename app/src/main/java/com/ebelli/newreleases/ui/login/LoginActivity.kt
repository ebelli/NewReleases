package com.ebelli.newreleases.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ebelli.newreleases.R
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse

/*
    Used to get the token access, not in the App flow

 */
class LoginActivity : AppCompatActivity() {

       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val builder = AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)

        builder.setScopes(arrayOf("streaming"))
        val request = builder.build()

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, data)

            when (response.type) {
                AuthenticationResponse.Type.TOKEN -> {
                    Log.d("Spotify token",response.accessToken)
                }
                AuthenticationResponse.Type.ERROR -> {
                    Log.d("Spotify",response.error)
                }
                AuthenticationResponse.Type.EMPTY -> {
                    Log.d("Spotify","response Empty")
                }
                else -> {
                    Log.d("Spotify", "Error")
                }
            }
        }
    }

    companion object {

        private val REQUEST_CODE = 1337
        private val REDIRECT_URI = "" //TODO Add Me
        private val CLIENT_ID = "" //TODO Add Me
    }
}