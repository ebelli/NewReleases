package com.ebelli.newreleases.di.modules

import com.ebelli.newreleases.domain.repositories.AlbumRepository
import com.ebelli.newreleases.data.repositories.AlbumRepositoryImpl
import org.koin.dsl.module

val repoModule = module {
    single <AlbumRepository> { return@single AlbumRepositoryImpl(get()) }
}