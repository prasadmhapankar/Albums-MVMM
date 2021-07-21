package com.jpmc.android_challenge.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpmc.android_challenge.data.AlbumsRepository
import com.jpmc.android_challenge.model.Album
import com.jpmc.android_challenge.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository : AlbumsRepository) : ViewModel() {

    companion object{
        const val TAG = "##MainViewModel##"
    }

    var albumsLiveData = MutableLiveData<Result<List<Album>>>()
    var sortedAlbumsLiveData = MutableLiveData<List<Album>>()

    fun getAlbums(){
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){
                repository.getAlbums()
            }
            response.data?.let { repository.saveAlbums(it) }
            albumsLiveData.postValue(response)
        }
    }

    fun getSortedAlbums(list: List<Album>){
        viewModelScope.launch {
            val sortedList = list.sortedBy { it.title }
            sortedAlbumsLiveData.postValue(sortedList)
        }
    }

}