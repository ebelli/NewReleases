package com.ebelli.newreleases.domain.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ebelli.newreleases.data.remote.ApiDataStore
import com.ebelli.newreleases.data.repositories.AlbumRepositoryImpl
import com.ebelli.newreleases.domain.model.Albums
import com.ebelli.newreleases.domain.model.Paging
import com.ebelli.newreleases.utils.get2Albums
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AlbumRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiDataStore: ApiDataStore

    private lateinit var albumRepository: AlbumRepository


    @Before
    fun setUp() {
        albumRepository = AlbumRepositoryImpl(apiDataStore)
    }


    @Test
    fun `get list of albums when calling endpoint`() {
        val albums = get2Albums()
        runBlockingTest {
            Mockito.`when`(apiDataStore.getAlbums())
                .thenReturn(albums)

            val result = albumRepository.getAlbums()
            Assert.assertEquals(result.size , 2)
            Assert.assertEquals(result[0].name , "Dire Straits")
            Assert.assertEquals(result[1].name , "Communiqué")
            Assert.assertEquals(result[1].releaseDate , "1979-06-15")
            Assert.assertEquals(result[1].artist , "Dire Straits")
        }
    }
}