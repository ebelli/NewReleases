package com.ebelli.newreleases.ui.main

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.ebelli.newreleases.R
import com.ebelli.newreleases.data.entities.AlbumEntity
import com.ebelli.newreleases.ui.detail.DetailActivity
import org.junit.Test

class DetailActivityTest {


    private val intent = Intent(ApplicationProvider.getApplicationContext(), DetailActivity::class.java)
        .putExtra(ALBUM_EXTRA, getAlbum())

    @Test
    fun elementsAreVisible() {
        val activityScenario: ActivityScenario<DetailActivity> =
            ActivityScenario.launch(intent)

            Espresso.onView(withId(R.id.detail_album_image))
                .check(ViewAssertions.matches(isDisplayed()))
            Espresso.onView(withId(R.id.detail_album_name))
                .check(ViewAssertions.matches(isDisplayed()))
            Espresso.onView(withId(R.id.detail_album_artist_name))
                .check(ViewAssertions.matches(isDisplayed()))
            Espresso.onView(withId(R.id.detail_album_artist_tracks_label))
                .check(ViewAssertions.matches(isDisplayed()))
            Espresso.onView(withId(R.id.detail_album_artist_tracks))
                .check(ViewAssertions.matches(isDisplayed()))

        activityScenario.close()
    }

    private fun getAlbum() = AlbumEntity(name = "Dire Straits", releaseDate = "1978-10-07", artist = "Dire Straits", totalTracks = 9,
        thumbnail = "", image= "", externalUrl = "")

}
