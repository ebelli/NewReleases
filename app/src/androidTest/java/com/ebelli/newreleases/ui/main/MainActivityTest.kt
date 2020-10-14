package com.ebelli.newreleases.ui.main

import android.app.Instrumentation
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.ebelli.newreleases.R
import org.junit.Rule
import org.junit.Test

class MainActivityTest {


    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun elementsAreVisible() {
        Espresso.onView(withId(R.id.progressBar))
            .check(ViewAssertions.matches(isDisplayed()))
        Thread.sleep(5000)
        Espresso.onView(withId(R.id.album_list))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun clickOnElement() {

        Intents.init()

        val expectedIntent = hasAction(Intent.ACTION_SEND)
        intending(expectedIntent).respondWith( Instrumentation.ActivityResult(0, null))

        Thread.sleep(5000)
        Espresso.onView(withId(R.id.album_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        intended(expectedIntent)
        Intents.release()
    }
}
