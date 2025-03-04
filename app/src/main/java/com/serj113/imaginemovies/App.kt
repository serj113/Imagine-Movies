package com.serj113.imaginemovies

import androidx.multidex.MultiDexApplication
import com.serj113.lib.startup.StartUpMeasurer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        StartUpMeasurer.start()
        ApplicationWrapperRunner.runWrapper(this)
    }
}
