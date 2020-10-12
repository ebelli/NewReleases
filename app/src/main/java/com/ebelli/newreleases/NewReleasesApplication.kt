package com.ebelli.newreleases

import android.app.Application
import com.ebelli.newreleases.di.modules.appModule
import com.ebelli.newreleases.di.modules.repoModule
import com.ebelli.newreleases.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewReleasesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewReleasesApplication)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}