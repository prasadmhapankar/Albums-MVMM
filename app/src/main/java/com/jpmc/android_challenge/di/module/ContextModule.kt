package com.jpmc.android_challenge.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ContextModule(private val context: Context) {

    @Provides
    @Singleton
    fun getContext(): Context {
        return context
    }

}