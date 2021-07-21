package com.jpmc.android_challenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jpmc.android_challenge.R
import com.jpmc.android_challenge.di.DaggerViewModelFactory
import com.jpmc.android_challenge.di.Injector
import com.jpmc.android_challenge.di.component.DaggerActivityComponent
import kotlinx.android.synthetic.main.activity_main.*
import com.jpmc.android_challenge.utils.Result
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "#MainActivity#"
    }

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory
    @Inject
    lateinit var linearLayoutManager : LinearLayoutManager
    lateinit var mainViewModel: MainViewModel
    private var albumsAdapter: AlbumsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val component = DaggerActivityComponent.builder()
                .appComponent(Injector.getAppComponent(this))
                .build()
        component.inject(this)
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        subscribeOnUi()
        setupView()
        mainViewModel.getAlbums()
    }

    private fun setupView() {
        recycler_view.layoutManager = linearLayoutManager
        albumsAdapter = AlbumsAdapter()
        val dividerItemDecoration = DividerItemDecoration(this, linearLayoutManager.orientation)
        recycler_view.addItemDecoration(dividerItemDecoration)
       recycler_view.adapter = albumsAdapter
    }

    private fun subscribeOnUi() {
        mainViewModel.albumsLiveData.observe(this, { response->
            when(response.status){
                Result.Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    response.data?.let {
                        mainViewModel.getSortedAlbums(it)
                    }
                }
                Result.Status.ERROR ->{
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                }
                Result.Status.LOADING ->{
                    progress_bar.visibility = View.VISIBLE
                }
            }
        })

        mainViewModel.sortedAlbumsLiveData.observe(this, { response->
            albumsAdapter?.notifyAlbums(response)
        })
    }
}