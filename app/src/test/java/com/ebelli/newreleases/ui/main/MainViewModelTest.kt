package com.ebelli.newreleases.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ebelli.newreleases.data.entities.AlbumEntity
import com.ebelli.newreleases.domain.repositories.AlbumRepository
import com.ebelli.newreleases.ui.utils.Result
import com.ebelli.newreleases.ui.utils.Status
import com.ebelli.newreleases.utils.getAlbumEntityList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var albumRepository: AlbumRepository

    @Mock
    private lateinit var albumObserver: Observer<Result<List<AlbumEntity>>>

    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<Result<List<AlbumEntity>>>

    private lateinit var viewModel: MainViewModel


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(albumRepository, testDispatcher)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when getting albums return error`() {
        runBlockingTest {
            Mockito.`when`(albumRepository.getAlbums())
                .thenThrow(RuntimeException())

            viewModel.albums.observeForever(albumObserver)
            viewModel.getAlbums()
        }
        Mockito.verify(albumObserver,Mockito.times(2)).onChanged(argumentCaptor.capture())

        val values = argumentCaptor.allValues
        Assert.assertEquals(Status.LOADING, values[0].status)
        Assert.assertEquals(Status.ERROR, values[1].status)

        viewModel.albums.removeObserver(albumObserver)
    }

    @Test
    fun `when getting albums return empty list throw error`() {
        runBlockingTest {
            Mockito.`when`(albumRepository.getAlbums())
                .thenReturn(emptyList())

            viewModel.albums.observeForever(albumObserver)
            viewModel.getAlbums()
        }
        Mockito.verify(albumObserver,Mockito.times(2)).onChanged(argumentCaptor.capture())

        val values = argumentCaptor.allValues
        Assert.assertEquals(Status.LOADING, values[0].status)
        Assert.assertEquals(Status.ERROR, values[1].status)

        viewModel.albums.removeObserver(albumObserver)
    }

    @Test
    fun `when getting albums return list of albums`() {
        runBlockingTest {
            Mockito.`when`(albumRepository.getAlbums())
                .thenReturn(getAlbumEntityList())

            viewModel.albums.observeForever(albumObserver)
            viewModel.getAlbums()
        }

        Mockito.verify(albumObserver,Mockito.times(2)).onChanged(argumentCaptor.capture())

        val values = argumentCaptor.allValues
        Assert.assertEquals(Status.LOADING, values[0].status)
        Assert.assertEquals(Status.SUCCESS, values[1].status)
        Assert.assertEquals(getAlbumEntityList()[1].name, values[1].data!![1].name)

        viewModel.albums.removeObserver(albumObserver)
    }
}
