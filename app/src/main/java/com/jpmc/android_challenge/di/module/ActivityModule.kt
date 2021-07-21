package com.jpmc.android_challenge.di.module

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jpmc.android_challenge.di.scope.PerActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {
    @Provides
    @PerActivityScope
    fun layoutManager(context: Context): LinearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

}