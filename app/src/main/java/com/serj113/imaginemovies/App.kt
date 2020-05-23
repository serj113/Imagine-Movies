package com.serj113.imaginemovies

import com.serj113.imaginemovies.di.DaggerInjector
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App: DaggerApplication() {

    private val injector = DaggerInjector
        .builder()
        .application(this)
        .build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return injector
    }
}