package com.jpmc.android_challenge.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jpmc.android_challenge.data.AlbumsRepository
import com.jpmc.android_challenge.model.Album
import com.jpmc.android_challenge.utils.Result
import com.jpmc.android_challenge.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule() //needed to test code with LiveData

    @get:Rule
    val testCoroutineRule = TestCoroutineRule() //enables the main dispatcher to use TestCoroutineDispatcher.

    @Mock
    private lateinit var repository: AlbumsRepository

    @Mock
    private lateinit var albumsObserver: Observer<Result<List<Album>>>

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnAlbumsSuccess(){
        testCoroutineRule.runBlockingTest{
            //Arrange
            doReturn(Result.success(emptyList<Album>()))
                .`when`(repository)
                .getAlbums()
            //Act
            val viewModel = MainViewModel(repository)
            viewModel.getAlbumsLiveData().observeForever(albumsObserver)
            viewModel.getAlbums()
            //Assert
            verify(albumsObserver).onChanged(Result.loading(null))
            verify(albumsObserver).onChanged(Result.success(emptyList()))
            viewModel.getAlbumsLiveData().removeObserver(albumsObserver)
            reset(repository)
            reset(albumsObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnAlbumsError(){
        testCoroutineRule.runBlockingTest{
            val errorMessage = "Something went wrong"
            //Arrange
            doReturn(Result.error(RuntimeException(errorMessage).toString(), null))
                    .`when`(repository)
                    .getAlbums()
            //Act
            val viewModel = MainViewModel(repository)
            viewModel.getAlbumsLiveData().observeForever(albumsObserver)
            viewModel.getAlbums()
            //Assert
            verify(albumsObserver).onChanged(
                    Result.error(java.lang.RuntimeException(errorMessage).toString(), null))
            viewModel.getAlbumsLiveData().removeObserver(albumsObserver)
            reset(repository)
            reset(albumsObserver)
        }
    }
}