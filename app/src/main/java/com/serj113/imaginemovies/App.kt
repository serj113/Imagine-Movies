package com.serj113.imaginemovies

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        ApplicationWrapperRunner.runWrapper(this)
    }
}