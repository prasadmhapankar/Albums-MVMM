package com.jpmc.android_challenge.di.component

import com.jpmc.android_challenge.di.module.ActivityModule
import com.jpmc.android_challenge.di.module.ViewModelFactoryModule
import com.jpmc.android_challenge.di.module.ViewModelModule
import com.jpmc.android_challenge.di.scope.PerActivityScope
import com.jpmc.android_challenge.ui.MainActivity
import dagger.Component

@Component(modules = [
    ViewModelFactoryModule::class,
    ViewModelModule::class,
    ActivityModule::class
], dependencies = [AppComponent::class])
@PerActivityScope
interface ActivityComponent {

    fun inject(activity : MainActivity)

}