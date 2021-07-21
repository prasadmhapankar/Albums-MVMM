package com.jpmc.android_challenge.di

import android.content.Context
import com.jpmc.android_challenge.di.component.AppComponent
import com.jpmc.android_challenge.di.component.DaggerAppComponent
import com.jpmc.android_challenge.di.module.ContextModule

object Injector {
    private var appComponent : AppComponent? = null

    fun getAppComponent(context : Context) : AppComponent {
        if(appComponent == null){
            appComponent = DaggerAppComponent.builder().contextModule(ContextModule(context)).build()
        }
        return appComponent!!
    }
}