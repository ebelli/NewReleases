package com.ebelli.newreleases.di.modules

import com.ebelli.newreleases.ui.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get(), Dispatchers.IO)
    }
}