package com.ebelli.newreleases.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebelli.newreleases.data.entities.AlbumEntity
import com.ebelli.newreleases.domain.repositories.AlbumRepository
import com.ebelli.newreleases.ui.utils.Resource
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel(private val albumRepository: AlbumRepository, private val coroutineContext: CoroutineContext): ViewModel() {

    private val _albums = MutableLiveData<Resource<List<AlbumEntity>>>()
    val albums: LiveData<Resource<List<AlbumEntity>>> = _albums

    fun getAlbums() {
        viewModelScope.launch(coroutineContext) {
            _albums.postValue(Resource.Loading())
            try {
                val album = albumRepository.getAlbums()
                if (album.isNullOrEmpty()) {
                    _albums.postValue(Resource.Error("Cannot retrieve albums"))
                } else {
                    _albums.postValue(Resource.Success(album))
                }
            } catch (e: Exception) {
                _albums.postValue(Resource.Error( e.toString()))
            }
        }
    }
}