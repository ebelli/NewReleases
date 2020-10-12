package com.ebelli.newreleases.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebelli.newreleases.data.entities.AlbumEntity
import com.ebelli.newreleases.domain.repositories.AlbumRepository
import com.ebelli.newreleases.ui.utils.Result
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel(private val albumRepository: AlbumRepository, private val coroutineContext: CoroutineContext): ViewModel() {

    private val _albums = MutableLiveData<Result<List<AlbumEntity>>>()
    val albums: LiveData<Result<List<AlbumEntity>>> = _albums

    fun getAlbums() {
        viewModelScope.launch(coroutineContext) {
            _albums.postValue(Result.loading(data = null))
            try {
                val album = albumRepository.getAlbums()
                if (album.isNullOrEmpty()) {
                    _albums.postValue(Result.error(null, "Cannot retrieve albums"))
                } else {
                    _albums.postValue(Result.success(album))
                }
            } catch (e: Exception) {
                _albums.postValue(Result.error(null, e.toString()))
            }
        }
    }
}