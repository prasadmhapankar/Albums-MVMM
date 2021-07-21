package com.jpmc.android_challenge.di.module

import androidx.lifecycle.ViewModel
import com.jpmc.android_challenge.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.jpmc.android_challenge.di.ViewModelKey

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel) : ViewModel

}