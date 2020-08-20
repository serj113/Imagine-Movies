package com.serj113.imaginemovies

import android.app.Application
import com.serj113.imaginemovies.wrapper.FlipperDeps
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        FlipperDeps.setup(this)
    }
}