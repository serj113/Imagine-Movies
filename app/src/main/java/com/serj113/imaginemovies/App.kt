package com.serj113.imaginemovies

import android.app.Application
import com.serj113.imaginemovies.lib.startup.StartUpMeasurer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        StartUpMeasurer.start()
        ApplicationWrapperRunner.runWrapper(this)
    }
}
