package com.jpmc.android_challenge.di.component

import android.content.Context
import com.jpmc.android_challenge.data.AlbumsRepository
import com.jpmc.android_challenge.di.module.ContextModule
import com.jpmc.android_challenge.di.module.DataModule
import com.jpmc.android_challenge.di.module.NetworkModule
import com.jpmc.android_challenge.di.module.ViewModelModule
import com.jpmc.android_challenge.mapper.AlbumsMapper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class, DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun getContext(): Context

    fun getRepository(): AlbumsRepository

    fun getAlbumsMapper(): AlbumsMapper

}